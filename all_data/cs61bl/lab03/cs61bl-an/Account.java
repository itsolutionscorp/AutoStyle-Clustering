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
		this.myParent = null;
	}
	
	public Account(int balance, Account parentAccount) {
		this.myBalance = balance;
		this.myParent = parentAccount;
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
	
	public boolean hasEnough(int amount) {
		Account current = this;
		int sum = balance();
		while (current.myParent != null) {
			sum = sum + current.myParent.balance();
			current = current.myParent;
		}
		return (sum >= amount);
	}
	public boolean withdraw(int amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
			return false;
		} else if (this.myBalance < amount) {
			if (myParent == null) {
				System.out.println("Insufficient funds");
				return false; 
			} else { 
				if (hasEnough(amount)) {
					int bal= balance();
					withdraw(bal);
					return myParent.withdraw(amount-bal);
				} else 
					System.out.println("Insufficient funds");
				return false; 
				}
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
	public Account myParent;
	/**
	 * Merge account "anotherAcct" into this one by
	 * removing all the money from anotherAcct and
	 * adding that amount to this one.
	 */
	
	public void merge(Account anotherAcct) {
		int amt = anotherAcct.balance();
		anotherAcct.withdraw(amt);
		deposit(amt);
		}
	}


