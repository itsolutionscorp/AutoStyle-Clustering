/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	Account parentAccount;
	public Account(int balance) {
		this.myBalance = balance;
		this.parentAccount = null;
	}
	public Account(int balance, Account overdraft) {
		this.myBalance = balance;
		this.parentAccount = overdraft;
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
	public boolean withdraw(int amount) 
	{
		boolean success = false;
		if (amount < 0) 
		{
			System.out.println("Cannot withdraw negative amount.");
		} 
		else if (this.myBalance < amount) 
		{	
			
			
			if (parentAccount != null)
			{    
				int drawable_total = myBalance;
				Account placeholder;
				placeholder = parentAccount;
				while (placeholder.parentAccount!=null) 
				{
					drawable_total = drawable_total + placeholder.balance();
					placeholder = placeholder.parentAccount;
			    }
				drawable_total = drawable_total + placeholder.balance();
				if(drawable_total > amount) 
				{
					parentAccount.withdraw(amount - myBalance);
					this.withdraw(myBalance);
				}
				else 
				{
					System.out.println("Insufficient funds(Checked overdraft Account as well)");  	
				}
			} 
			else
			{
			 System.out.println("Insufficient funds");
			}
		} 
		else 
		{
			this.myBalance = this.myBalance - amount;
			success = true;
		}
		return success;
	}

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.myBalance;
	}

    public void merge (Account anotherAcct) {
        int placeholder = anotherAcct.balance();
        anotherAcct.myBalance = 0;
        this.myBalance = this.balance() + placeholder;
    }
	// instance variables
	private int myBalance;

}
