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
	
	public Account(int initBalance, Account parent)
	{
		this.myBalance = initBalance;
		parentAccount = parent;
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
		} 
		else if (this.myBalance < amount) {
			if(parentAccount == null)
			{
				System.out.println("Insufficient funds");
				return false;
			}
			else
			{
				if(this.myBalance + parentAccount.myBalance < amount)
				{
					System.out.println("Insufficient funds");
					parentAccount.withdraw(amount - this.myBalance);
					return false;
				}
				else
				{
					parentAccount.myBalance = parentAccount.myBalance - (amount - this.myBalance);
					this.myBalance = 0;
					System.out.println ("Withdrawn. Balance of parent is " + parentAccount.myBalance);
					return true;
				}
			}
		} 
		else {
			this.myBalance = this.myBalance - amount;
			return true;
		}
	}
	
	public void merge(Account anotherAcct) {
		this.myBalance = this.myBalance + anotherAcct.myBalance;
		anotherAcct.myBalance = 0;
		System.out.println("Mike's balance is "+this.myBalance);
		//System.out.println("Other account balance " + anotherAcct.myBalance);
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
