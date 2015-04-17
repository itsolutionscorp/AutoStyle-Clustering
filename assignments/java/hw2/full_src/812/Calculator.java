import list.EquationList;

public class Calculator {

    private EquationList history = new EquationList("I am sentinel", 0, null);
    private int size;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
    	int xBit, yBit, sumBit, sum = 0, carry = 0, pos = 0;

    	while (pos < 32) {
    		xBit = getBit(x, pos);
    		yBit = getBit(y, pos);

    		if (carry == 1 && (xBit & yBit) == 1) {  								//Three ones
    			carry = 1;
    			sum = setBit(sum, pos);
    		} else if ((carry == 1 && (xBit ^ yBit) == 1) || (xBit & yBit) == 1) { 	//Two ones
    			carry = 1;
    		} else if (carry == 1 || (xBit ^ yBit) == 1 ) { 						//Only one
    			carry = 0;
    			sum = setBit(sum, pos);
    		} else { 																//All zeroes
    			carry = 0;
    		}
    		pos += 1;
    	}
    	return sum;
    }

    private int getBit(int x, int i) {
    	return (x >> i) & 1;
    }

    private int setBit(int x, int i){
    	return x | (1 << i);
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
    	int pos = 0, sum = 0;
    	while (pos < 32) {
    		if (getBit(y, pos) == 1) {
    			sum = add(sum, x << pos);
    		}
    		pos += 1;
    	}
    	return sum;
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
    	history = new EquationList(equation, result, history);
    	size += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    	printHistory(size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
    	EquationList temp = history;
        while ( n > 0 && temp.next != null ) {
        	System.out.println(temp.equation + " = " + temp.result);
        	temp = temp.next;
        	n -= 1;
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
        history = new EquationList("I am sentinel", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    	EquationList temp = history;
    	int sum = 0;
        while (temp.next != null ) {
        	sum += temp.result;
        	temp = temp.next;
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
    	EquationList temp = history;
    	int product = 1;
        while (temp.next != null ) {
        	product *= temp.result;
        	temp = temp.next;
        }
        return product;
    }
}