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
		this.myParent = null;
	}
	public Account myParent;
	
	public Account(int balance, Account anotherAcct) {
		this.myBalance = balance;
		this.myParent = anotherAcct;
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
	public void withdraw(int amount) {
		int remaining_balance;
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
		} else if (this.myBalance < amount) {
			if (this.myParent != null) {
				if ((this.myBalance + getParent()) > amount) {
					remaining_balance = amount - this.myBalance;
					this.myBalance = 0;
					myParent.myBalance -= remaining_balance;
				} else {
					if (this.myParent.myParent != null) {
						remaining_balance = amount - this.myBalance;
						myParent.withdraw(remaining_balance);
						this.myBalance = 0;
					} else {
						System.out.println("Insufficient funds");
					}
				}}
		} else {
			this.myBalance = this.myBalance - amount;
		}
	}

	public int getParent() {
		return myParent.myBalance;
	}
	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

	public void merge (Account anotherAcct) {
		this.myBalance += anotherAcct.myBalance;
		anotherAcct.withdraw(anotherAcct.myBalance);
	}
	
	// instance variables
	private int myBalance;
}