package Part3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GUI extends JFrame {

    private LinkedList<Account> globalAccounts;
    private JTextArea showAll;  // Text area to display all account details
    private JTextField accDeposit, depositInput, accWithdraw, withdrawInput, acc1Transfer, acc2Transfer, transferAmount;

    public GUI(LinkedList<Account> accounts) {
        super("Banking System");
        this.globalAccounts = accounts;
        initializeComponents();
        layoutComponents();
        //updateAccountData();
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        // Initialize all the UI components
        showAll = new JTextArea(10, 30);
        showAll.setEditable(false);
        accDeposit = new JTextField(10);
        depositInput = new JTextField(10);
        accWithdraw = new JTextField(10);
        withdrawInput = new JTextField(10);
        acc1Transfer = new JTextField(10);
        acc2Transfer = new JTextField(10);
        transferAmount = new JTextField(10);
        
    }

    private void layoutComponents() {
        // Create main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        // Create data panel with GridLayout for input fields
        JPanel dataPanel = new JPanel(new GridLayout(4, 3, 5, 5));

        // Adding input fields
        dataPanel.add(new JLabel("Account to Deposit:"));
        dataPanel.add(accDeposit);
        dataPanel.add(depositInput);
        dataPanel.add(new JLabel("Account to Withdraw:"));
        dataPanel.add(accWithdraw);
        dataPanel.add(withdrawInput);
        dataPanel.add(new JLabel("From Account:"));
        dataPanel.add(acc1Transfer);
        dataPanel.add(new JLabel("To Account:"));
        dataPanel.add(acc2Transfer);
        dataPanel.add(transferAmount);

        // Adding buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createButton("Deposit", "deposit"));
        buttonPanel.add(createButton("Withdraw", "withdraw"));
        buttonPanel.add(createButton("Transfer", "transfer"));
        buttonPanel.add(createButton("Show All", "showAll"));

        // Setting the layout
        panel.add(new JScrollPane(showAll), BorderLayout.NORTH);
        panel.add(dataPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private JButton createButton(String title, String actionCommand) {
        // Create a button with the given title and action command
        JButton button = new JButton(title);
        button.setActionCommand(actionCommand);
        button.addActionListener(new HandlerClass());
        return button;
    }
    private class HandlerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (e.getActionCommand()) {
                    case "deposit" -> processDeposit();
                    case "withdraw" -> processWithdrawal();
                    case "transfer" -> processTransfer();
                    case "showAll" -> updateAccountData();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GUI.this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void processDeposit() {
        int accountNumber = Integer.parseInt(accDeposit.getText());
        double amount = Double.parseDouble(depositInput.getText());
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit((int) amount);
            updateAccountData();
        }
    }

    private void processWithdrawal() {
        int accountNumber = Integer.parseInt(accWithdraw.getText());
        double amount = Double.parseDouble(withdrawInput.getText());
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw((int) amount);
            updateAccountData();
        }
    }

private void processTransfer() {
    int fromAccountNumber = Integer.parseInt(acc1Transfer.getText());
    int toAccountNumber = Integer.parseInt(acc2Transfer.getText());
    double amount = Double.parseDouble(transferAmount.getText());
    Account fromAccount = findAccount(fromAccountNumber);
    Account toAccount = findAccount(toAccountNumber);
    if (fromAccount != null && toAccount != null && amount > 0) {
        if (fromAccount.getBalance() >= amount) { // Ensure sufficient balance
            fromAccount.withdraw((int) amount); // Withdraw the amount from fromAccount
            toAccount.deposit((int) amount); // Deposit the amount into toAccount
            updateAccountData();
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds for transfer", "Transfer Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Invalid account details or amount", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void updateAccountData() {
        StringBuilder sbAllData = new StringBuilder();
        for (Account account : globalAccounts) {
            sbAllData.append("Name: ").append(account.getFirstName()).append(" ").append(account.getLastName())
                    .append(", Account Number: ").append(account.getAccountNum()).append(", Balance: ")
                    .append(account.getBalance()).append("\n");
        }
        showAll.setText(sbAllData.toString());
    }

    private Account findAccount(int accountNumber) {
        for (Account account : globalAccounts) {
            if (account.getAccountNum() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Example to test the GUI
        LinkedList<Account> accounts = new LinkedList<>();
        new GUI(accounts);
    }
}
