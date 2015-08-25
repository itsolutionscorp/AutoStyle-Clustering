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
	public Account(int balance, Account parentAccount){
		this.parentAccount = parentAccount;
		this.myBalance = balance;
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
			if (parentAccount == null){
				System.out.println("Insufficient funds");
			}
			else{
				parentAccount.merge(this);
				parentAccount.withdraw(amount);
			}
			return false;
		} else {
			this.myBalance = this.myBalance - amount;
			return true;
		}
	}
	public void merge (Account another){
		while (another.balance()!= 0){
			this.deposit(1);
			another.withdraw(1);
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
	private Account parentAccount;
}
