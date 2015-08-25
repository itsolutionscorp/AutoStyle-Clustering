/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	public Account parent;
	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.myBalance = balance;
		this.parent = null;
	}

	public Account(int balance, Account parentAcct) {
		this.myBalance = balance;
		this.parent = parentAcct;
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
			if (this.parent == null) {
				System.out.println("No parent, Insufficient funds");
				return false;
			}
			else {
				int value = this.myBalance;
				int needed = amount - value;
				if (this.parent.withdraw(needed) == true){
					this.myBalance = 0;
				}
			}
			
			
			
			System.out.println("Insufficient funds");
			return false;
		} else {
			this.myBalance = this.myBalance - amount;
			return true;
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
		int first = this.myBalance;
		int second = anotherAcct.balance();
		this.myBalance = first + second; 
		anotherAcct.myBalance = 0;
		
		
	}

}
