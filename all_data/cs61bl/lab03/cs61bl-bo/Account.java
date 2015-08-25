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
		this.parentAccount = null;
	}
	
	public Account(int balance, Account parentAcc) {
		this.myBalance = balance;
		this.parentAccount = parentAcc;
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
		boolean success = false;
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
		} else if (this.myBalance < amount) {
			if(parentAccount==null) {
				System.out.println("Insufficient funds");
			}
			else {
				success = parentAccount.withdraw(amount-this.myBalance);
				if(success) {
					this.myBalance = 0;
				}
			}
		} else {
			success = true;
			this.myBalance = this.myBalance - amount;
		}
		return success;
	}

	public void merge (Account anotherAcct) {
	    this.myBalance += anotherAcct.balance();
	    anotherAcct.withdraw(anotherAcct.balance());
	}
	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	// instance variables
	private int myBalance;
	private Account parentAccount;
}
