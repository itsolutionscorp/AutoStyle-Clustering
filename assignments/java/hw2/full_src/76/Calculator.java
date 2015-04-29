import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	public Calculator() {
	}
	
	EquationList L;
	
	
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
    	
    	while (y != 0) {
            int carry = (x & y) ;

            x = x ^ y; 

            y = carry << 1;
        }
    	y = 0;
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
    public int multiply(int x, int y) {
    	 int current = 0;
         while (y != 0) 
         {
        	 if (x == 0) {
        		 return current;
        	 }
             if ((y & 1) != 0) 
             {
                 current = add(current,x); 
             }
             x = x << 1;              
             y = y >> 1;             
         }
         return current ;
    
    	
    	
       
        
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
    	EquationList Newest = new EquationList(equation,result,L);
    	L = Newest;
    	
    	
    	
    	
    	
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    	EquationList P = L;
    	if (P == null) {
        	return;
        } 
        while (P.next != null) {
        	System.out.println(P.equation + " = " + P.result);
        P = P.next;
    }  
        System.out.println(P.equation + " = " + P.result);
    
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
    	
        EquationList P = L;
        if (P == null) {
        	return;
        } 
        for(int counter = 1; counter <= n-1; counter++) {
        P = P.next;
    }    
        System.out.println(P.equation + " = " + P.result);
  
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
    	if (L == null) {
        	return;
        } 
         L = L.next;
     }    

    

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
    	if (L == null) {
        	return;
        } 
    	while (L.next != null) {
        L = L.next;
    }  
    	L = L.next;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    	
    	if (L == null) {
        	return 0;
        } 
    	
        EquationList P = L;
        int cumsum = 0;
        
        while (P.next != null) {
        	cumsum = cumsum + P.result;
        P = P.next;
    }  
        cumsum = cumsum + P.result;
        return cumsum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        
    	if (L == null) {
        	return 0;
        } 
    	
        EquationList P = L;
        int cumsum = 1;
        
        while (P.next != null) {
        	cumsum = cumsum * P.result;
        P = P.next;
    }  
        cumsum = cumsum * P.result;
        return cumsum;
    }
}