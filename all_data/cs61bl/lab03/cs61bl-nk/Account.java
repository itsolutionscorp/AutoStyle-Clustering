/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	Account parentAccount;
	/**
	 * Initialize an account with the given balance.
	 */
	
	
	public Account(int balance) {
		this.myBalance = balance;
		this.parentAccount = null;
		
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

	public Account(int balance, Account parent) {
		this.myBalance = balance;
		this.parentAccount = parent; 
	}
	/**
	 * Subtract the given amount from the account if possible. If the amount
	 * would leave a negative balance, print an error message and leave the
	 * balance unchanged.
	 */
	public boolean withdraw(int amount) {
		boolean Success = false;
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return Success;
		} else if (this.myBalance < amount) {
			if (this.parentAccount != null) {
				if (parentAccount.myBalance > (amount - this.myBalance)) {
					int remainingamount = amount - this.myBalance;
					this.myBalance = 0;
					parentAccount.withdraw(remainingamount); 
					Success = true;
					return Success;
				} else {
					System.out.println("Insufficient funds from parent account.");
					return Success;
				}				
			}
			System.out.println("Insufficient funds");
			return Success;
		} else {
			this.myBalance = this.myBalance - amount;
			Success = true;
			return Success;
		}
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
	    // TODO Put your own code here
		this.myBalance = this.myBalance + anotherAcct.myBalance; 
		anotherAcct.myBalance = 0;
	}

}
