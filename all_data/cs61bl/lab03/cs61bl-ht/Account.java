/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

	/**
	 * Initialize an account with the given balance.
	 */
	public Account(int balance) {
		this(balance, null);
	}

    /**
     * Create a bank account with the given balance and "parent" account for overdraft protection
     */
    public Account(int balance, Account parentAccount) {
        if (balance < 0)
            throw new IllegalArgumentException("Cannot make a bank account with a negative balance!");
        this.balance = balance;
        this.parentAccount = parentAccount;
    }

	/**
	 * Add the given amount to the account.
	 */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.balance = this.balance + amount;
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
		} else if (balance < amount) {
            if (parentAccount != null && balance + parentAccount.balance() > amount) {
                parentAccount.withdraw(amount - balance);
                balance = 0;
                return true;
            } else {
                System.out.println("Insufficient funds.");
                return false;
            }
		} else {
			this.balance = this.balance - amount;
			return true;
		}
	}

    public void merge(Account otherAccount) {
        deposit(otherAccount.balance());
        otherAccount.balance = 0;
    }

	/**
	 * Return the number of dollars in the account.
	 */
	public int balance() {
		return this.balance;
	}

	// instance variables
	private int balance;
    private Account parentAccount;

}
