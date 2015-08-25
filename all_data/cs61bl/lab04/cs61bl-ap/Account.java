/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	
	public Account myPA;
	public Account(int balance) {
		this.myBalance = balance;
		this.myPA = null;
	}

	public Account(int balance, Account parentAccount){
		this.myBalance = balance;
		this.myPA = parentAccount;
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
		}else if (this.myBalance > amount ){
			this.myBalance -= amount;
			return true;
		}
		else if (this.myBalance < amount && this.myPA == null){
			System.out.println("Insufficient funds");
			return false;
		}
		else{
			int new_amount = amount - this.myBalance;
			boolean withdrawable = this.myPA.withdraw(new_amount);
			if (withdrawable){
				this.myBalance = 0;
				return true;
			}else{
				return false;
			}
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
	public void merge(Account anotherAccount){
		this.deposit(anotherAccount.myBalance);
		anotherAccount.myBalance = 0;
	}
	


}
