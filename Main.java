package Part3;

import java.util.LinkedList;

import javax.swing.JFrame;

public class Main {
	    public static void main(String[] args) {
	        
	     // Create a LinkedList of accounts
	        LinkedList<Account> accounts = new LinkedList<>();
	        String file = "C:/Users/acer/OneDrive/Desktop/java/bankingsystem/src/main/java/coursework/bankingsystem/Accounts.csv";

	        // Create an object of ReadAccounts class
	        ReadAccounts readAccounts = new ReadAccounts(file);

	        LinkedList<String> firstNames = readAccounts.getFirstNames();
	        LinkedList<String> lastNames = readAccounts.getLastNames();
	        LinkedList<Integer> accountList = readAccounts.getAccounts();
	        LinkedList<Integer> balanceList = readAccounts.getBalances();
	        

	        // Populate the LinkedList of accounts
	        for (int i = 0; i < firstNames.size(); i++) {
	           accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountList.get(i), balanceList.get(i)));
	        }

       // Test the accounts
      for (Account account : accounts) {
           // Print account information
            System.out.println("Account created - Name: " + account.getFirstName() + " " + account.getLastName() +", Account Number: " + account.getAccountNum() + ", Balance: " + account.getBalance());
            account.deposit(100); 
             System.out.println("After deposit, new balance: " + account.getBalance());
        }
	        
	       GUI gui = new GUI(accounts);
	        gui.setSize(1000,1000);
	        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        gui.setVisible(true);
	    }
	    }