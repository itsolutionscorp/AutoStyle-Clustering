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

	/**
	 * Add the given amount to the account.
	 */
	public Account(int balance, Account parent){
		this.myBalance = balance;
		this.parentAccount = parent; 
	}
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
	public boolean withdraw(int amount) { boolean money=false;
		if (amount < 0) {System.out.println("Cannot withdraw negative amount.");
			
		} else 
			if (this.myBalance < amount)
			{if (this.parentAccount!= null)
		{if (this.parentAccount.withdraw(amount-this.myBalance))
		{
		this.myBalance =0;
		money = true;}else{System.out.println("Insufficient funds");}
			
		}
		else{
			System.out.println("Insufficient funds");}
			
		} else {
			this.myBalance = this.myBalance - amount;money = true;
			
		}
	System.out.println(money);
	return money;
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}
	public void merge(Account anotherAct) {
		this.deposit(anotherAct.myBalance);
		anotherAct.withdraw(anotherAct.myBalance);
	}

	// instance variables
	private int myBalance;
	private Account parentAccount;

}
