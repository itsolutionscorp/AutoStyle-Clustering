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
		if (this.balance() >= amount) {
			if (amount < 0) {
				System.out.println("Cannot withdraw negative amount.");
				return false;
			} else {
				this.myBalance = this.myBalance - amount;
				return true;
			}
		} else if (this.parentAccount != null && parentAccount.withdraw(amount-this.balance())) {
			this.myBalance = 0;
			return true;
			
		} else {
			System.out.println("Overdraft!");
			return false;
		}
	}
			
			
			
			
			
// Original withraw method
//		//without overdraft protection	
//		if (amount < 0) {
//			System.out.println("Cannot withdraw negative amount.");
//			return success;
//		} else if (this.myBalance < amount) {
//			System.out.println("Insufficient funds");
//			return success;
//		} else {
//			this.myBalance = this.myBalance - amount;
//			success = true;
//			return success;
//		}
//	}
	
	public void merge (Account anotherAcct) {
		this.deposit(anotherAcct.balance());
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
	Account parentAccount;

}
