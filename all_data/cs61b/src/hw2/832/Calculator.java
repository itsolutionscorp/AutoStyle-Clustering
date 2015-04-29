import list.EquationList;


public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

	public EquationList equations = null;
	
	public int add(int A, int B) {
        int mask = 1; 
        int result = 0;
        int carry = 0;

        for (int i = 1; i <= 32; i++) {
            int a = A & mask;
            int b = B & mask;
            result |= a ^ b ^ carry;
            carry = ((a & b) | ((a ^ b) & carry)) << 1;
            mask <<= 1;
        }
        return result;
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
    	 int a = x;
    	    int b = y;
    	    int result = 0;
    	    while (b != 0)
    	    {
    	        if ((b & 1) != 0)
    	        {
    	            result = result + a;
    	        }
    	        a <<= 1;
    	        b >>>= 1;
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
        if (equations == null) {
        	equations = new EquationList(equation, result, null);
        }
        else {
        	EquationList prev = null;
        	EquationList ptr = equations;
        	while (ptr != null) {
        		prev = ptr;
        		ptr = ptr.next;
        	}
        	EquationList newList = new EquationList(equation, result, null);
        	prev.next = newList;
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
    	// find length equations
    	// printHistory(length)
    	int length_Equations = 0;
    	EquationList prev = null;
    	EquationList ptr = equations;   
    	while (ptr != null) {
    		prev = ptr;
    		ptr = ptr.next;
    		length_Equations++;
    	}
    	printHistory(length_Equations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printHistory(int n) {
    	// figure out length of equations
    	
    	int length_Equations = 0;
    	EquationList prev = null;
    	EquationList ptr = equations;   
    	while (ptr != null) {
    		prev = ptr;
    		ptr = ptr.next;
    		length_Equations++;
    	}
    	if (n > length_Equations) {
    		System.out.println("Not enough equations.");
    	}
    	else { // n <= length_Equations
    		prev = null;
    		ptr = equations;
    		EquationList[] OutputEquations = new EquationList[n];
    		int num_ofLoops = 0;
    		int i = 0;
    		while (ptr != null) {
    			prev = ptr;
    			if (i >= length_Equations - n) {
    				OutputEquations[num_ofLoops] = prev;
    				num_ofLoops++;
    			}
    			ptr = ptr.next;
    			i++;
    		}
    		for (int j = OutputEquations.length - 1; j >= 0; j--) {
    			System.out.println(OutputEquations[j].equation + " = " + OutputEquations[j].result);	
    		}
    	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
    	int length_Equations = 0;
    	EquationList prev = null;
    	EquationList ptr = equations;   
    	while (ptr != null) {
    		prev = ptr;
    		ptr = ptr.next;
    		length_Equations++;
    	}
    	if (length_Equations == 1) {
    		equations = null;
    	}
    	else if (length_Equations == 2) {
    		equations.next = null;
    	}
    	else {
    		EquationList prev1 = equations;
    		EquationList prev2 = equations.next;
    		EquationList pointer = equations.next.next;
    		while (pointer != null) {
    			prev1 = prev2;
    			prev2 = pointer;
    			pointer = pointer.next;    		
    		}
    		prev1.next = null;
    	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
    	equations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total_Sum = 0;
        EquationList prev = null;
        EquationList ptr = equations;
        if (equations == null) {
        	return 0;
        }
        while (ptr != null) {
        	prev = ptr;
        	ptr = ptr.next;
        	total_Sum = total_Sum + prev.result;
        }
        return total_Sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int total_Prod = 1;
        EquationList prev = null;
        EquationList ptr = equations;
        if (equations == null) {
        	return 1;
        }
        while (ptr != null) {
        	prev = ptr;
        	ptr = ptr.next;
        	total_Prod = total_Prod * prev.result;
        }
        return total_Prod;
    }
}