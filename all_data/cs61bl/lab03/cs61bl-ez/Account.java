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
		parentAccount = null;
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
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
		} else if (this.myBalance < amount) {
			if (parentAccount == null) {
				System.out.println("No parent Account and insufficient funds");
				return false;
			} else if (parentAccount.withdraw(amount - this.myBalance)) {
				this.myBalance = 0;
				return true;
			} else {
				System.out.println("You and your parent account do not have sufficient funds");
				return false;
			}
		} else {
			this.myBalance = this.myBalance - amount;
			return true;
		}
	}

	/**
	 * Merge account "anotherAcct" into this one by removing all the money from
	 * anotherAcct and adding that amount to this one
	 */
	public void merge(Account anotherAcct) {
		int otherBalance = anotherAcct.balance();
		anotherAcct.withdraw(otherBalance);
		this.deposit(otherBalance);
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	public Account(int initialBalance, Account parentAccount) {
		this.myBalance = initialBalance;
		this.parentAccount = parentAccount;
	}

	// instance variables
	private int myBalance;
	private Account parentAccount;

}
