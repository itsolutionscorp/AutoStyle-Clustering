//read https://geeki.wordpress.com/2007/12/12/adding-two-numbers-with-bitwise-and-shift-operators/ to get understand strategies for binary addition; no code copied

import list.EquationList;

public class Calculator {
    public EquationList history = new EquationList(null, 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
	
    public int add(int x, int y) {
		int xorXY = x ^ y;
		int carriedValues = x & y;  //find the bit position where the values sum to 10
		
		while (carriedValues != 0) {
			int xCopy = xorXY;   //make copy for loop
			int positionCarryValues = carriedValues << 1; //shift left by one to add the carried value
			xorXY = xCopy ^ positionCarryValues;
			carriedValues = xCopy & positionCarryValues;
		}
		
		return xorXY;
				
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
		
	// this helper function takes in a byte and a count and return the value of the byte at the count (either 1 or 0)
	private int getLastByte(int x, int count) {  
		return ((x << count) >>> 31);
	}
	
	/* Reasoning: take x as a given string of bytes. depending on the value of the the sucessive
	* right-most byte in y, x is either added to itself with one shift left (yByte = 1), or nothing 
	* is added (yByte = 0). Watched https://www.youtube.com/watch?v=QW0XNZPyWUk for a primer on binary 
	* multiplication
	*/
	public int multiply(int x, int y) {
		int copyX = x;   //makes a copy of x
		int countX = 0; // increment x after each loop
		int result = 0; // in each loop add to this value
		int countY = 31; //counter for the next successive right-most byte in y; increments down
		
		while (countX < 32) {  
			
			if (getLastByte(y, countY) == 1) {
				result = add(result, (copyX << countX));
				countY = add(countY, -1);
				countX = add(countX, 1);
			}
			else {
				countX = add(countX, 1);
				countY = add(countY, -1);
			}
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
		if (history.equation == null) {
			history.equation = equation;
			history.result = result;
					
		}
		else {
			history = new EquationList(equation, result, history);
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
		EquationList historyCopy = history;	
		if (historyCopy.equation != null) {
			while (historyCopy != null) {
				System.out.println(historyCopy.equation + " = " + historyCopy.result);	
				historyCopy = historyCopy.next;
			}	
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
		int i = n;
		EquationList historyCopy = history;
		
		if (historyCopy.equation != null) {
			while (i > 0) {
				System.out.println(historyCopy.equation + " = " + historyCopy.result);
				historyCopy = historyCopy.next;
				i = i - 1;
			}
		}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
		EquationList historyCopy = history;
		int result = 0;
        if (historyCopy.equation == null) {
        	return result;
        }
		while (historyCopy != null) {
			result = add(result, historyCopy.result);
			historyCopy = historyCopy.next;
		}
		return result;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
		EquationList historyCopy = history;
		int result = 1;
        if (historyCopy.equation == null) {
        	return result;
        }
		while (historyCopy != null) {
			result = multiply(result, historyCopy.result);
			historyCopy = historyCopy.next;
		}
		return result;
    }
}