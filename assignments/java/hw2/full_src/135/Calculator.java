import list.EquationList;

public class Calculator {
    public EquationList equationHistory = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
     
    public int add(int x, int y) {
        int remainder = 1;
        while (remainder != 0) {
        	remainder = (x & y) << 1;
        	x = x ^ y;
        	y = remainder;
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
     
    public int multiply(int x, int y) {
       	if (x == 0 || y == 0) {
       		return 0;
       	} else if (x == 1) {
       		return y;
       	} else if (y == 1) {
       		return x;
       	} else if (y == 2) {
       		return (x << 1);
       	} else if (x == 2) {
       		return (y << 1);
       	} else if (y < 0) {
       		return -multiply(x, -y);
       	} else if (x < 0) {
       		return -multiply(-x, y);
       	}
       	
       	int original = x;
       	x = x << yPower(y);
        
        int counter = 0;
        while (counter < yRemainder(y)) {
        	x = add(x, original);
        	counter = counter + 1;
        }
        
        return x;
    }
    
    public int yPower(int y) {
    	int nearestTwo = 1;
    	while ((nearestTwo << 1) < y) {
    		nearestTwo = nearestTwo << 1;
    	}
    	int power = 0;
    	while (nearestTwo != 1) {
    		nearestTwo = nearestTwo >>> 1;
    		power = power + 1;
    	}
    	return power;
    }
    
    public int yRemainder(int y) {
    	int nearestTwo = 1;
    	while ((nearestTwo << 1) < y) {
    		nearestTwo = nearestTwo << 1;
    	}
    	return add(y, -nearestTwo);
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
        equationHistory = new EquationList(equation, result, equationHistory);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList p = equationHistory;
        while (p != null) {
        	System.out.println(p.equation + " = " + p.result);
        	p = p.next;
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
        EquationList p = equationHistory;
        int counter = 0;
        while (counter < n) {
        	System.out.println(p.equation + " = " + p.result);
        	p = p.next;
        	counter = counter + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationHistory = equationHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList p = equationHistory;
        while (p != null) {
        	sum = sum + p.result;
        	p = p.next;
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
        int product = 1;
        EquationList p = equationHistory;
        while (p != null) {
        	product = product * p.result;
        	p = p.next;
        }
        return product;
    }
}