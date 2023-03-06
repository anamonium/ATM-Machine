import databaseManagement.DatabaseManagement;
import entity.CardEntity;
import exception.NonActiveCard;
import exception.NonExistingCard;
import exception.NotEnoughMoney;
import exception.SameCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class transferForm {
    private JPanel panel1;
    private JTextField cardNoTextField;
    private JTextField amountTextField;
    private JButton confirmButton;
    private JButton cancelButton;

    static JFrame transferFrame;
    private static transferForm transferForm;
    private CardEntity card;
    DatabaseManagement dbm;

    public transferForm(CardEntity cardEntity, DatabaseManagement database) {
        this.card = cardEntity;
        this.dbm = database;

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cardNo = cardNoTextField.getText();
                    String sAmo = amountTextField.getText();
                    double amo = Math.abs(Double.parseDouble(sAmo));
                    dbm.transferMoney(amo, card, cardNo);
                    transferFrame.setVisible(false);
                }catch(NotEnoughMoney | NonExistingCard | NumberFormatException | SameCard | NonActiveCard ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error" ,JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferFrame.setVisible(false);
            }
        });
    }

    static void createView(CardEntity card, DatabaseManagement database){
        transferFrame = new JFrame();
        transferForm = new transferForm(card, database);
        transferFrame.setContentPane(transferForm.panel1);
        transferFrame.pack();
        transferFrame.setVisible(true);
    }
}
