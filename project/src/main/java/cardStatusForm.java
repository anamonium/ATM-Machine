import databaseManagement.DatabaseManagement;
import entity.CardEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cardStatusForm extends atmForm{
    private JPanel panel1;
    private JLabel statusLabel;
    private JButton noButton;
    private JButton yesButton;
    private JLabel changeStatusLabel;

    static JFrame statusFrame;
    private static cardStatusForm statusForm;
    private CardEntity card;
    DatabaseManagement dbm;

    public cardStatusForm(CardEntity card, DatabaseManagement database) {

        this.card = card;
        this.dbm = database;

        if(card.getStatus() == 1){
            statusLabel.setText("The status of your card is: ACTIVE.");
            changeStatusLabel.setText("Do you want to DEACTIVATE it?");
        }
        else if(card.getStatus() == 0){
            statusLabel.setText("The status of your card is: INACTIVE.");
            changeStatusLabel.setText("Do you want to ACTIVATE it?");
        }


        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String)JOptionPane.showInputDialog(
                        statusFrame,
                        "To confirm input your actual PIN number",
                        "Confirmation",
                        JOptionPane.PLAIN_MESSAGE,null,null,"");

                if(s.equals(card.getPin())){
                    dbm.changeStatus(card);
                    statusFrame.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong PIN");
                }
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusFrame.setVisible(false);
            }
        });
    }

    static void createView(CardEntity card, DatabaseManagement database){
        statusFrame = new JFrame();
        statusForm = new cardStatusForm(card, database);
        statusFrame.setContentPane(statusForm.panel1);
        statusFrame.pack();
        statusFrame.setVisible(true);

    }
}
