import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList history = null;
	int hsize = 0;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
       
    	int i=0;
    	int xbit = 0;
    	int ybit = 0;
    	int cbit = 0;
    	int res = 0;
    	
    	    	
    	while (i<32)
    	{
    		xbit = (x >>> i) & 1;
    		ybit = (y >>> i) & 1;
    		
    	    		
    		if ((xbit & ybit & cbit) == 1)
    		{
    			res = res | (1 << i);
    			cbit = 1;
    		}
    		else if((xbit & ybit) == 1 || (xbit & cbit) == 1 || (ybit & cbit) == 1 )
    		{
    			res = res | (0 << i);
    			cbit = 1;
    		}else
    			{
    			res = res | (( xbit | ybit | cbit) << i);
    			cbit = 0;
    			}
    		    		
    		i++;
    	}
    	
        return res;
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
        // YOUR CODE HERE
    	
    	
    	int ybit = 0;
    	
    	int i = 0;
    	int res = 0;
    	
    	while (i<32)
    	{
    		
    		ybit = (y >>> i) & 1;
    		
        		if(ybit == 1)
        			res = add(res , (x << (i)) );
        	    	
    		i++;
    	}
    			
    	
      return res;  
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
        // YOUR CODE HERE
    	
    	
    	history = new EquationList(equation, result, history);
    	hsize++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    	
    	printHistory(hsize);
    	
        // YOUR CODE HERE
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
       EquationList curr = history;
       int c = 0;
       while (c < n && curr!=null)
       {
    	   System.out.println(curr.equation + " = " + curr.result);
    	   curr = curr.next;
    	   c++;
    			  
       }
    	
    	// YOUR CODE HERE
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
    	
    	history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
       
    	history = null;
    	// YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
    	EquationList curr = history;
    	int sum = 0;
    	while (curr!=null)
    	{
    		sum+=curr.result;
    		curr = curr.next;
    	}
    	
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
    	EquationList curr = history;
    	int prod = 1;
    	while (curr!=null)
    	{
    		prod*=curr.result;
    		curr = curr.next;
    	}
    	
        return prod;
        
    }
}