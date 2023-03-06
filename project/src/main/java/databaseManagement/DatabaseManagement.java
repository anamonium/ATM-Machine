package databaseManagement;

import entity.AccountEntity;
import entity.CardEntity;
import exception.NonActiveCard;
import exception.NonExistingCard;
import exception.NotEnoughMoney;
import exception.SameCard;
import org.hibernate.Session;
import javax.persistence.*;

public class DatabaseManagement implements DatabaseManagementInterface{

    EntityManagerFactory fac;
    EntityManager man;
    EntityTransaction tr;

    public DatabaseManagement(){
        fac = Persistence.createEntityManagerFactory("default");
        man = fac.createEntityManager();
        tr = man.getTransaction();
    }

    @Override
    public CardEntity validateInputData(String cardNo, String pinNo){
        TypedQuery<CardEntity> checkPIN = man.createNamedQuery("validatePIN.byCard", CardEntity.class);
        checkPIN.setParameter(1, cardNo);
        checkPIN.setParameter(2, pinNo);
        if(checkPIN.getResultList().size() == 0)
            return null;
        else
            return checkPIN.getSingleResult();
    }

    public AccountEntity getAccountByCard(String cardNo){
        Session s = man.unwrap(org.hibernate.Session.class);
        tr.begin();
        TypedQuery<AccountEntity> acc = s.createNamedQuery("getAccount", AccountEntity.class);
        acc.setParameter(1, cardNo);
        AccountEntity a = acc.getResultList().get(0);
        tr.commit();
        return a;
    }

    @Override
    public void withdrawMoney(double amount, CardEntity card) throws NotEnoughMoney, NonActiveCard{

        if(card.getStatus() == 0)
            throw new NonActiveCard();

        tr.begin();
        StoredProcedureQuery tmp = man.createNamedStoredProcedureQuery("withdrawMoney");
        tmp.setParameter("amount", amount);
        tmp.setParameter("cardNo", card.getCardNumber());
        tmp.execute();
        int result = (int)tmp.getOutputParameterValue("result");
        tr.commit();
        if(result == 0)
            throw new NotEnoughMoney();
    }

    @Override
    public void depositMoney(double amount, CardEntity card) throws NonActiveCard{

        if(card.getStatus() == 0)
            throw new NonActiveCard();

        Session s = man.unwrap(org.hibernate.Session.class);
        tr.begin();
        TypedQuery<AccountEntity> account =s.createNamedQuery("getAccount", AccountEntity.class);
        account.setParameter(1, card.getCardNumber());
        AccountEntity a = account.getResultList().get(0);
        a.setBalance(a.getBalance()+amount);
        s.update(a);
        tr.commit();

    }

    @Override
    public void transferMoney(double amount, CardEntity card1, String cardNo) throws NonExistingCard, NotEnoughMoney, SameCard, NonActiveCard {

        if(card1.getStatus() == 0)
            throw new NonActiveCard();
        if(card1.getCardNumber().equals(cardNo))
            throw new SameCard();

        tr.begin();
        Session s = man.unwrap(org.hibernate.Session.class);
        TypedQuery<AccountEntity> acc = s.createNamedQuery("getAccount", AccountEntity.class);
        TypedQuery<Integer> car = s.createNamedQuery("getStatus", Integer.class);
        acc.setParameter(1, cardNo);
        car.setParameter(1, cardNo);

        if((acc.getResultList().size())<1){
            tr.rollback();
            throw new NonExistingCard();
        }

        if(car.getSingleResult() == 0){
            tr.rollback();
            throw new NonActiveCard();
        }


        StoredProcedureQuery tmp = man.createNamedStoredProcedureQuery("withdrawMoney");
        tmp.setParameter("amount", amount);
        tmp.setParameter("cardNo", card1.getCardNumber());
        tmp.execute();
        int result = (int)tmp.getOutputParameterValue("result");

        if(result != 0){
                AccountEntity a = acc.getResultList().get(0);
                a.setBalance(a.getBalance() + amount);
                s.update(a);
                tr.commit();
        }
        else{
            throw new NotEnoughMoney();
        }
    }

    @Override
    public double getBalance(CardEntity card){
        Session s = man.unwrap(org.hibernate.Session.class);
        TypedQuery<AccountEntity> acc = s.createNamedQuery("getAccount", AccountEntity.class);
        acc.setParameter(1, card.getCardNumber());
        AccountEntity a = acc.getResultList().get(0);
        return a.getBalance();
    }

    @Override
    public void changePIN(CardEntity c, String newPin) throws NonActiveCard{

        if(c.getStatus() == 0)
            throw new NonActiveCard();

        Session s = man.unwrap(org.hibernate.Session.class);
        tr.begin();
        c.setPin(newPin);
        s.update(c);
        tr.commit();
    }

    @Override
    public void changeStatus(CardEntity c){
        Session s = man.unwrap(org.hibernate.Session.class);
        tr.begin();
        if(c.getStatus() == 0)
            c.setStatus(1);
        else if(c.getStatus() == 1)
            c.setStatus(0);
        s.update(c);
        tr.commit();
    }
}
