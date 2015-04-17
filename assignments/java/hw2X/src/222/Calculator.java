import list.EquationList;

public class Calculator {
    EquationList list;
	EquationList copy;
	boolean newlist = true;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
		if ( y == 0)	{
			return x;
		}
		if ( x == 0)	{
			return y;
		}
		return add((x & y) << 1, x ^ y);
		
    }
	

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
		if (y == 0 || x == 0)	{
			return 0;
		}
		if (y == 1)	{
			return x;
		}
		if ((y & 1) == 1)	{
			return add(multiply(x << 1,y >>> 1), x);
		}
		return multiply(x << 1, y >>> 1);
		
		
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
		
		if(newlist)	{
			list = new EquationList(equation, result, null);
			newlist = false;
			copy = list;
		}
		else	{
			copy.next = new EquationList(equation, result, null);
			copy = copy.next;
		}
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
		EquationList copy3 = list;
		int i = 1;
        while(copy3 != null)	{
			printHistory(i);
			copy3 = copy3.next;
			i++;
		}
		   
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
		EquationList copy2 = list;
		int i = 0;
        while(copy2 != null)	{
			copy2 = copy2.next;
			i++;
		}
		copy2 = list;
		while(n != i)	{
			copy2 = copy2.next;
			n++;
		}
		System.out.println(copy2.equation +" = " + Integer.toString(copy2.result));
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList copy3 = list;
		if (copy3.next == null)	{
			clearHistory();
		}
		else{
			while(copy3.next.next != null)	{
				copy3 = copy3.next;
			}
			copy3.next = null;
		
		copy = list;
		while(copy.next != null)	{
			copy = copy.next;
		}
		}
		
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
		list = null;
		newlist = true;
        
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList copy3 = list;
		int total = 0;
		 while(copy3 != null)	{
			 total += copy3.result;
			copy3 = copy3.next;
		}
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList copy3 = list;
		int total = 1;
		 while(copy3 != null)	{
			 total *= copy3.result;
			copy3 = copy3.next;
		}
        return total;
    }
}
