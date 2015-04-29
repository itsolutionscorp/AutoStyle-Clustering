import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList history;
    int counter = -1;
    


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

   

     public int add(int x, int y) {
	 /**	String xBinString;
	String yBinString;

      
       xBinString = Integer.toBinaryString(x);
       yBinString = Integer.toBinaryString(y);
      
 
        int xBinInt;
        int yBinInt;
       xBinInt = Integer.parseInt(xBinString);
       yBinInt = Integer.parseInt(yBinString); **/
       while (y != 0) {
        int carry = x & y;  
        x = x ^ y; 
        y = carry << 1;
    }
    return x;
}

    

    

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply1(int x, int y) {
        // YOUR CODE HERE
        return (x * y);
    }

    public int multiply(int x, int y) {
	/**	String xBinString;
	String yBinString;
     
       xBinString = Integer.toBinaryString(x);
       yBinString = Integer.toBinaryString(y);
 
        int xBinInt;
        int yBinInt;
      
       xBinInt = Integer.parseInt(xBinString);
       yBinInt = Integer.parseInt(yBinString); **/
int result = 0;
 
    while (y != 0)
    {
 
        if ((y & 1) != 0)
        {
	  result = add(result, x);
        }
        x <<= 1;
        y >>= 1;
    }
    return result;
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
	if (counter == -1) {
	    history = new EquationList(equation, result, null);
	       counter = counter + 1;
	     }
     else { 
	 history = new EquationList(equation, result, history); }
       	 counter = counter + 1; 
    }
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	    int i; 
	    EquationList hist;
	    hist = history;
	    for(i = 0; i < counter; i = i + 1) {
	     System.out.println(hist.equation + " = " + hist.result);
	     hist = hist.next;
	
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
        int i;
	EquationList hist;
	hist = history;
	for(i = 0; i < n; i = i + 1) {
	     System.out.println(hist.equation + " = " + hist.result);
	     hist = hist.next;
	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
	history = history.next;
	counter = counter - 1;
	
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        EquationList history;
	counter = -1;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum;
	EquationList hist;
	int i;
	hist = history;
	sum = 0;
	    if (counter == -1) {
		return 0;
	    }
	    else { 
		for(i = 0; i < counter; i = i + 1) {
		    sum = sum + hist.result;
		    hist = hist.next;
		}
		return sum; 
    }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int product;
	EquationList hist;
	int i;
	hist = history;
	product = 1;
	    if (counter == -1) {
		return 1;
	    }
	    else { 
		for(i = 0; i < counter; i = i + 1) {
		    product = product * hist.result;
		    hist = hist.next;
		}
		return product; 
    }
}
}
