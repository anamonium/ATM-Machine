package databaseManagement;

import entity.CardEntity;
import exception.NonActiveCard;
import exception.NonExistingCard;
import exception.NotEnoughMoney;
import exception.SameCard;

public interface DatabaseManagementInterface {

    CardEntity validateInputData(String cardNo, String pinNo);

    void withdrawMoney(double amount, CardEntity card) throws NotEnoughMoney, NonActiveCard;

    void depositMoney(double amount, CardEntity card) throws NonActiveCard;

    void transferMoney(double amount, CardEntity card1, String cardNo) throws NonExistingCard, NotEnoughMoney, SameCard, NonActiveCard;

    double getBalance(CardEntity card);

    void changePIN(CardEntity c, String newPin) throws NonActiveCard;

    void changeStatus(CardEntity c);
}
