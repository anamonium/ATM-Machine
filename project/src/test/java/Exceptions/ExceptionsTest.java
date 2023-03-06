package Exceptions;

import databaseManagement.DatabaseManagement;
import entity.CardEntity;
import exception.NonActiveCard;
import exception.NonExistingCard;
import exception.NotEnoughMoney;
import exception.SameCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ExceptionsTest {

    DatabaseManagement dbm = new DatabaseManagement();
    @Test
    public void testNonActiveCardException() {
        CardEntity c = dbm.validateInputData("4444586906277706", "1111");
        NonActiveCard thrown = assertThrows(
                NonActiveCard.class, () -> {
                    dbm.withdrawMoney(20, c);
                }
        );

        assertTrue(thrown.getMessage().contains("This card is not active"));
    }

    @Test
    public void testNonExistingCardException(){
        CardEntity c = dbm.validateInputData("4257223962819504", "2222");
        NonExistingCard thrown = assertThrows(
                NonExistingCard.class, () ->{
                    dbm.transferMoney(30,c, "729389027289" );
                }
        );

        assertTrue(thrown.getMessage().contains("This card does not exist!"));
    }

    @Test
    public void testNotEnougnMoneyException(){
        CardEntity c = dbm.validateInputData("4257223962819504", "2222");
        NotEnoughMoney thrown = assertThrows(
            NotEnoughMoney.class, () ->{
                    dbm.withdrawMoney(9000, c);
                }
        );

        assertTrue(thrown.getMessage().contains("Not enough nomey in the account!"));
    }


    @Test
    public void testSameCardException(){
        CardEntity c = dbm.validateInputData("4257223962819504", "2222");
        SameCard thrown = assertThrows(
                SameCard.class, () ->{
                    dbm.transferMoney(200, c, "4257223962819504");
                }
        );

        assertTrue(thrown.getMessage().contains("The same card has been entered"));
    }
}
