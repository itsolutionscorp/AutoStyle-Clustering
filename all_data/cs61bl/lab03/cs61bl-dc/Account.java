/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.myBalance = balance;
		
	}

	/**
	 * Add the given amount to the account.
	 */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.myBalance = this.myBalance + amount;
		}
	}

	/**
	 * Subtract the given amount from the account if possible. If the amount
	 * would leave a negative balance, print an error message and leave the
	 * balance unchanged.
	 */
	public boolean withdraw(int amount) {
		boolean success; 
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			success = false;
		} else if (this.myBalance < amount) {
			if (parentAccount != null) {
				int leftovers = 0;
				if (amount > this.myBalance) {
					leftovers = amount - this.myBalance;
					this.myBalance = 0;
					parentAccount.withdraw(leftovers);
				}else {
					this.myBalance -= amount;
				}
				success = true;
			}else {
			System.out.println("Insufficient funds");
			success = false;
			}
		} else {
			this.myBalance = this.myBalance - amount;
			success = true;
		}
		return success;
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	
	public void merge (Account anotherAcct) {
		this.myBalance+=anotherAcct.balance();
		anotherAcct.myBalance = 0;
		
	}
	
	private Account parentAccount;	
	
	
	public Account (int initialBalance, Account parent) {
		this.myBalance =initialBalance;
		parentAccount = parent;
		
	}

}
