import databaseManagement.DatabaseManagement;
import entity.CardEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class atmForm{
    private JButton withdawButton;
    private JPanel panel1;
    private JButton depositButton;
    private JButton checkBalanceButton;
    private JButton exitButton;
    private JButton transferButton;
    private JButton changeStatusOfCardButton;
    private JButton changePINOfTheButton;


    static JFrame atmFrame;
    private static atmForm atmForm;
    protected CardEntity card;

    protected DatabaseManagement dbm;

    public atmForm(CardEntity cardEntity, DatabaseManagement databaseManagemen) {
        this.card = cardEntity;
        this.dbm = databaseManagemen;

        withdawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationForm.createView("withdraw", card, dbm);
            }
        });
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String balance = "Your balance is " + dbm.getBalance(card);
                JOptionPane.showMessageDialog(atmFrame, balance,"Balance", JOptionPane.PLAIN_MESSAGE);
            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferForm.createView(card, dbm);
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationForm.createView("deposit",card, dbm);
            }
        });
        changeStatusOfCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = "";
                cardStatusForm.createView(card, dbm);

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atmFrame.dispose();
                System.exit(0);
            }
        });
        changePINOfTheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePIN.createView(card, dbm);
            }
        });
    }

    public atmForm() {
    }

    static void createView(CardEntity card, DatabaseManagement database){
        atmFrame = new JFrame();
        atmForm = new atmForm(card, database);
        atmFrame.setContentPane(atmForm.panel1);
        atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atmFrame.pack();
        atmFrame.setVisible(true);
    }


}
