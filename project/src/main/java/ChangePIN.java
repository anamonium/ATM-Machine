import databaseManagement.DatabaseManagement;
import entity.CardEntity;
import exception.NonActiveCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePIN {
    private JPanel panel1;
    private JPasswordField newPINTextField;
    private JButton cancelButton;
    private JButton confirmButton;

    static JFrame changePINFrame;
    private static ChangePIN changePINForm;
    private CardEntity card;
    DatabaseManagement dbm;

    public ChangePIN(CardEntity cardEntity, DatabaseManagement database) {

        this.dbm = database;
        this.card = cardEntity;

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String)JOptionPane.showInputDialog(
                        changePINFrame,
                        "To confirm input your actual PIN number",
                        "Confirmation",
                        JOptionPane.PLAIN_MESSAGE,null,null,"");

                if(s.equals(card.getPin())){
                    //String sPIN = newPINTextField.getText();
                    char [] pin = newPINTextField.getPassword();
                    String pin1 = "";
                    for (char i:pin)
                        pin1+=i;

                    String a = pin1;
                    for (int i = 0; i < pin1.length(); i++){
                        if (!Character.isDigit(pin1.charAt(i)) || i > 3){
                            JOptionPane.showMessageDialog(null, "Wrong format of a PIN code");
                            a = "";
                            break;
                        }
                    }
                    if(a.equals(pin1)) {
                        try {
                            dbm.changePIN(card, pin1);
                            changePINFrame.setVisible(false);
                        }catch(NonActiveCard ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    changePINFrame.setVisible(false);

                }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong PIN");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePINFrame.setVisible(false);
            }
        });
    }

    static void createView(CardEntity card, DatabaseManagement database){
        changePINFrame = new JFrame();
        changePINForm = new ChangePIN(card, database);
        changePINFrame.setContentPane(changePINForm.panel1);
        changePINFrame.pack();
        changePINFrame.setVisible(true);
    }
}
