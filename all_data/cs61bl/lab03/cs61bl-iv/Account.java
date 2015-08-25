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
	
	public Account(int balance, Account parent) {
		this.myBalance = balance;
		this.parentAccount = parent;
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
		boolean canWithdraw = false;
		
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
		} else if (this.myBalance < amount) {
			if (this.parentAccount != null) {
				if (this.myBalance + this.parentAccount.myBalance >= amount) {
					this.parentAccount.withdraw(amount - this.myBalance);
					this.withdraw(this.myBalance);
					canWithdraw = true;
				} else { // we want to check the parent of the parent
					this.parentAccount.withdraw(amount - this.myBalance);
				}
			} else { // base case, if parent == null
				System.out.println("Insufficient funds");
			}
		} else {
			this.myBalance = this.myBalance - amount;
			canWithdraw = true;
		}
		return canWithdraw;
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	public void merge(Account anotherAcct){
		this.myBalance += anotherAcct.balance();
		anotherAcct.myBalance = 0;
	}
	
	// instance variables
	private int myBalance;
	Account parentAccount;

}
