package Part3;

import java.util.LinkedList;

import javax.swing.JFrame;

public class Main {
	    public static void main(String[] args) {
        /*      Account account1 = new Account("Bishant", "Rajbanshi", 1, 5000);

		Account account2 = new Account("Lucifer", "Morningstar", 2, 6000);

		System.out.println("Balance of account1: " + account1.getBalance());

		System.out.println("Balance of account2: " + account2.getBalance());

		account1.deposit(5000);

		System.out.println("Balance of account1 after deposit: " + account1.getBalance());

		account2.withdraw(3000);

		System.out.println("Balance of account2 after withdrawal: " + account2.getBalance());

		Transaction t = new Transaction();
		t.transfer(account1, account2, 1000);
		System.out.println("Final balance of account1: " + account1.getBalance());
		System.out.println("Final balance of account2: " + account2.getBalance());*/
	        
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