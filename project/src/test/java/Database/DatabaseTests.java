package Database;

import databaseManagement.DatabaseManagement;
import entity.AccountEntity;
import entity.CardEntity;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTests {
    EntityManagerFactory fac = Persistence.createEntityManagerFactory("default");
    EntityManager man = fac.createEntityManager();

    @Test
    public void getAccountQueryTest(){
        TypedQuery<AccountEntity> acc = man.createNamedQuery("getAccount", AccountEntity.class);
        acc.setParameter(1, "5359468226822208");
        assertEquals("82124046349940360172738812", acc.getResultList().get(0).getAccountNumber());
    }

    @Test
    public void validatePINQueryTest(){
        TypedQuery<CardEntity> checkPIN = man.createNamedQuery("validatePIN.byCard", CardEntity.class);
        checkPIN.setParameter(1, "5359468226822208");
        checkPIN.setParameter(2, "1234");
        String c = checkPIN.getResultList().get(0).getCardNumber();
        assertEquals("5359468226822208", c);
    }

    @Test
    public void withdrawMoneyProcedureTest(){
        StoredProcedureQuery tmp = man.createNamedStoredProcedureQuery("withdrawMoney");
        double amount = 9999;
        tmp.setParameter("amount", amount);
        tmp.setParameter("cardNo", "4257223962819504");
        tmp.execute();
        int result = (int)tmp.getOutputParameterValue("result");

        assertEquals(0, result);
    }
}
