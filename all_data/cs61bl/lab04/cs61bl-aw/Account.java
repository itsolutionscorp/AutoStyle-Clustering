public class Account {

	/**
	 * Add a parentnAccount instance variable
	 */
	Account parentAccount;

	
    /**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this.parentAccount = null;
		this.myBalance = balance;
	}

	/** 
	 * Add two-argument constructor
	 */
	public Account(int balance,Account parentAccount) {
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
		//if the account balance has enough money
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
		}
		else if (this.myBalance >= amount){
			this.myBalance = this.myBalance-amount;
			return true;
		}
		//if the account does not have enough money
		else {
			//if there is no more parent account the result will be false
			if (parentAccount == null){
				System.out.println("Insufficient funds");
				return false;
			}
			// if there is a parent
			else {
					//with parent, we have enough money
					Boolean weHaveEnough = amount <= (this.myBalance + parentAccount.myBalance);
					if (weHaveEnough){
						parentAccount.withdraw(amount-this.myBalance);
						this.myBalance = 0;
						return true;
						
					}
					//with parent, we do not have enough money
					else{
						if (parentAccount.withdraw(amount-this.myBalance))
							this.myBalance = 0;
						return false;
					}
				}
			}
		}

				
				
		

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}
	/**
	 * Merge account "anotherAcct" into this one by
	 * removing all the money from anotherAcct and
	 * adding that amount to this one.
	 */
	public void merge (Account anotherAcct) {
	    // TODO Put your own code here	
		
		this.myBalance=this.myBalance+anotherAcct.myBalance;
		anotherAcct.myBalance=0;
		
	}
	


	// instance variables
	private int myBalance;

}