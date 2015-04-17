import list.EquationList;



public class Calculator
{
    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList history = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS add() is a method which computes the
     * sum of two integers x and y using only bitwise operators.
     * 
     * @param x
     *            is an integer which is one of two addends
     * @param y
     *            is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y)
    {
	int sum = 0;
	int temp = 0;
	for (int i = 0; i < 32; i++)
	{
	    int tempx = (x >>> i) & 1;
	    int tempy = (y >>> i) & 1;
	    boolean current_i = true;
	    if ((tempx ^ tempy) == 1)
	    {
		temp = 1 << i;
	    }
	    else if ((tempx & tempy) == 1)
	    {
		temp = (1 << i) << 1;
		current_i = false;
	    }
	    else
	    {
		temp = 0;
	    }
	    while ((sum & temp) != 0)
	    {
		temp = temp << 1;
		if (current_i)
		{
		    sum = sum ^ (1 << (i));
		}
		else
		{
		    sum = sum ^ ((1 << i) << 1);
		}
	    }
	    sum = sum | temp;

	}

	return sum;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS multiply() is a method which
     * computes the product of two integers x and y using only bitwise
     * operators.
     * 
     * @param x
     *            is an integer which is one of the two numbers to multiply
     * @param y
     *            is an integer which is the other of the two numbers to
     *            multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y)
    {
	int product = 0;

	for (int i = 0; i < 32; i++)
	{
	    int tempy = (y >>> i) & 1;
	    int tempx = 0;
	    if (tempy == 1)
	    {
		tempx = (x << i);
	    }
	    product = this.add(product, tempx);

	}
	return product;
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and the
     * corresponding result. Note: You only need to save equations, not other
     * commands. See spec for details.
     * 
     * @param equation
     *            is a String representation of the equation, ex. "1 + 2"
     * @param result
     *            is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result)
    {

	this.history = new EquationList(equation, result, this.history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line. Please print in
     * the following format: Ex "1 + 2 = 3"
     **/
    public void printAllHistory()
    {
	EquationList temp = this.history;
	while (temp != null)
	{
	    System.out.println(temp.equation + " = " + temp.result);
	    temp = temp.next;

	}
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS printHistory()
     * prints each equation (and its corresponding result), most recent equation
     * first with one equation per line. A maximum of n equations should be
     * printed out. Please print in the following format: Ex "1 + 2 = 3"
     **/
    public void printHistory(int n)
    {
	EquationList temp = this.history;
	for (int i = 0; i < n; i++)
	{
	    if (temp != null)
	    {
		System.out.println(temp.equation + " = " + temp.result);
		temp = temp.next;

	    }
	}
    }

    /**
     * TASK 6: CLEAR AND UNDO undoEquation() removes the most recent equation we
     * saved to our history.
     **/
    public void undoEquation()
    {
	this.history = this.history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO clearHistory() removes all entries in our history.
     **/
    public void clearHistory()
    {
	this.history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeSum() computes the
     * sum over the result of each equation in our history.
     * 
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum()
    {
	int sum = 0;
	EquationList temp = this.history;
	while (temp != null)
	{
	    sum = this.add(sum, temp.result);
	    temp = temp.next;
	}
	return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeProduct() computes
     * the product over the result of each equation in history.
     * 
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct()
    {
	int product = 1;
	EquationList temp = this.history;
	while (temp != null)
	{
	    product = this.multiply(product, temp.result);
	    temp = temp.next;

	}

	return product;

    }
}
