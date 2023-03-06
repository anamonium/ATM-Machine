import databaseManagement.DatabaseManagement;
import entity.CardEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class validateCard {
    private JPanel panel1;
    private JTextField cardNoTextFielf;
    private JPasswordField PINPasswordField;
    private JLabel PINLabel;
    private JLabel CardNoLabel;
    private JLabel label;
    private JButton ConfirmButton;

    static JFrame validateCardFrame;
    private static validateCard validateCardForm;
    protected DatabaseManagement databaseManagement;

    public validateCard() {
        databaseManagement = new DatabaseManagement();
        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNo = cardNoTextFielf.getText();
                char [] pin = PINPasswordField.getPassword();
                String pin1 = "";
                for (char i:pin)
                    pin1+=i;
                CardEntity card = databaseManagement.validateInputData(cardNo, pin1);
                if(card == null)
                    JOptionPane.showMessageDialog(null, "Niepoprawny PIN");
                else{
                    atmForm.createView(card, databaseManagement);
                    validateCardFrame.setVisible(false);
                }
            }
        });
    }

    public static void main(String[] args){
        validateCardFrame = new JFrame();
        validateCardForm = new validateCard();
        validateCardFrame.setContentPane(validateCardForm.panel1);
        validateCardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validateCardFrame.pack();
        validateCardFrame.setVisible(true);
    }
}
