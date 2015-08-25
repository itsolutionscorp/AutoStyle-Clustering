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
    public Account(int balance, Account parentAccount){
    	this.myBalance = balance;
    	this.parentAccount = parentAccount;
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
			
			
			
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
		} else if (this.myBalance < amount) {
					if (parentAccount != null && amount - myBalance - parentAccount.myBalance  > 0){
						amount = amount - myBalance;
						if (parentAccount.withdraw(amount)) {
						myBalance = 0;
						System.out.println("stop");
						return parentAccount.withdraw(amount);}
						return false;
						
						} else if (parentAccount != null && amount - myBalance - parentAccount.myBalance  < 0) {
							amount = amount - myBalance;
							myBalance = 0;
							parentAccount.myBalance = parentAccount.myBalance - amount;
							return true;
						}
				
//						boolean t= parentAccount.withdraw(amount - myBalance);
	//					myBalance = 0;
		//				return t;
							else {
				System.out.println("Insufficient funds");
				return false;
			}
					
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
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
    Account parentAccount;
    
    public void merge (Account anotherAcct){
    	this.myBalance = this.myBalance + anotherAcct.myBalance;
    	anotherAcct.myBalance = 0;
    }
}
