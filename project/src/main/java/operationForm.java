import databaseManagement.DatabaseManagement;
import entity.CardEntity;
import exception.NonActiveCard;
import exception.NotEnoughMoney;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class operationForm {
    private JPanel panel1;
    private JLabel questionLabel;
    private JRadioButton a10RadioButton;
    private JRadioButton a20RadioButton;
    private JRadioButton a50RadioButton;
    private JRadioButton a100RadioButton;
    private JRadioButton a200RadioButton;
    private JButton confirmButton;
    private JRadioButton otherRadioButton;
    private JTextField amountTextField;
    private JButton cancelButton;

    static JFrame operationFrame;
    private static operationForm operationForm;
    String typeOfOperation;
    private CardEntity card;
    DatabaseManagement dbm;

    public operationForm(String type, CardEntity cardEntity, DatabaseManagement database) {

        this.card = cardEntity;
        this.dbm = database;
        this.typeOfOperation = type;
        String tmp = "";
        if (typeOfOperation.equals("withdraw")){
            tmp = questionLabel.getText();
            tmp += " withdraw?";
            questionLabel.setText(tmp);
        }
        else{
            tmp = questionLabel.getText();
            tmp += " deposit?";
            questionLabel.setText(tmp);
        }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeOfOperation.equals("withdraw")){
                    try {
                        if (a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.withdrawMoney(10, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.withdrawMoney(20, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.withdrawMoney(50, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.withdrawMoney(100, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.withdrawMoney(200, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && otherRadioButton.isSelected()) {
                            try {
                                String sAmo = amountTextField.getText();
                                double amo = Math.abs(Double.parseDouble(sAmo));
                                if ((amo % 10) != 0 || amo > 10000)
                                    throw new NumberFormatException();
                                dbm.withdrawMoney(amo, card);
                                operationFrame.setVisible(false);

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Wrong format of a number", null, JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Only one choice is allowed", null, JOptionPane.WARNING_MESSAGE);
                        }
                    }catch(NotEnoughMoney | NonActiveCard ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    try {
                        if (a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.depositMoney(10, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.depositMoney(20, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.depositMoney(50, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && a100RadioButton.isSelected() && !a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.depositMoney(100, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && a200RadioButton.isSelected() && !otherRadioButton.isSelected()) {
                            dbm.depositMoney(200, card);
                            operationFrame.setVisible(false);
                        }
                        else if (!a10RadioButton.isSelected() && !a20RadioButton.isSelected() && !a50RadioButton.isSelected()
                                && !a100RadioButton.isSelected() && !a200RadioButton.isSelected() && otherRadioButton.isSelected()) {
                            try {
                                String sAmo = amountTextField.getText();
                                double amo = Math.abs(Double.parseDouble(sAmo));
                                if ((amo % 10) != 0 || amo > 10000)
                                    throw new NumberFormatException();
                                dbm.depositMoney(amo, card);
                                operationFrame.setVisible(false);
                            } catch (NumberFormatException | NonActiveCard ex) {
                                JOptionPane.showMessageDialog(null, "Wrong format of a number");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Only one choice is allowedw");

                        }
                    }catch(NonActiveCard ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationFrame.setVisible(false);
            }
        });
    }

    static void createView(String type, CardEntity card, DatabaseManagement database){
        operationFrame = new JFrame();
        operationForm = new operationForm(type, card, database);
        operationFrame.setContentPane(operationForm.panel1);
        operationFrame.pack();
        operationFrame.setVisible(true);

    }
}
