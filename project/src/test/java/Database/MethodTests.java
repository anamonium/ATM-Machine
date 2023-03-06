package Database;

import databaseManagement.DatabaseManagement;
import entity.AccountEntity;
import entity.CardEntity;
import exception.NonActiveCard;
import exception.NonExistingCard;
import exception.NotEnoughMoney;
import exception.SameCard;
import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

public class MethodTests {

    DatabaseManagement dbm = new DatabaseManagement();
    CardEntity card = dbm.validateInputData("4257223962819504", "6666");

    @Test
    public void depositMoneyTest(){
        try {
            AccountEntity acc = dbm.getAccountByCard(card.getCardNumber());
            double amount = 50;
            double balanceBefore = acc.getBalance();
            dbm.depositMoney(amount, card);
            double balanceAfter = acc.getBalance();
            assertEquals(balanceBefore + amount, balanceAfter);
            dbm.withdrawMoney(amount,card);
        }catch(NonActiveCard | NotEnoughMoney ex){
            fail(ex.getMessage());
        }
    }


    @Test
    public void withdrawMoneyTest(){
        try {
            AccountEntity acc = dbm.getAccountByCard("4257223962819504");
            double accBalanceBefore = acc.getBalance();
            double amount = 50;
            dbm.withdrawMoney(amount, card);
            DatabaseManagement dbm2 = new DatabaseManagement();
            AccountEntity acc1 = dbm2.getAccountByCard("4257223962819504");
            double accBalanceAfter = acc1.getBalance();
            dbm2.depositMoney(amount, card);

            assertEquals(accBalanceAfter, accBalanceBefore - amount);

        }catch(NonActiveCard | NotEnoughMoney ex){
            fail(ex.getMessage());
        }

    }


    @Test
    public void transferMoneyTest(){
        try {

            AccountEntity acc = dbm.getAccountByCard("5359468226822208");
            double accBalanceBefore = acc.getBalance();
            double amount = 50;
            dbm.transferMoney(amount, card, "5359468226822208");
            double accBalanceAfter = acc.getBalance();
            assertEquals(accBalanceAfter, accBalanceBefore + amount);
            DatabaseManagement dbm2 = new DatabaseManagement();
            CardEntity card1 = dbm2.validateInputData("5359468226822208", "1234");
            dbm2.withdrawMoney(amount, card1);
            dbm2.depositMoney(amount, card);
        }catch(NonExistingCard | NotEnoughMoney | SameCard | NonActiveCard ex){
            fail(ex.getMessage());
        }

    }

    @Test
    public void changeStatusTest(){
        int status = card.getStatus();
        dbm.changeStatus(card);
        int status2 = card.getStatus();
        assertEquals(status, abs(status2-1));
        dbm.changeStatus(card);
    }

    @Test
    public void changePINTest(){
        try {
            String PIN = card.getPin();
            String newPin = "3333";
            dbm.changePIN(card, newPin);
            assertEquals(newPin, card.getPin());
            dbm.changePIN(card, PIN);
        }catch(NonActiveCard ex){
            fail(ex.getMessage());
        }

    }


}
