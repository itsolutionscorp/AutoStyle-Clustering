import list.EquationList;

public class Calculator {
	
	private EquationList list;
	
    public Calculator() {
    	
    }

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int result = x ^ y;
        int carry = (x & y) << 1;
        if (carry != 0) {
        	return add(result, carry);
        }
        return result;
    } // Note: Received significant help on bitwise operations from Stack Overflow

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
    	while (b != 0) {
    		if ((b & 1) != 0) {
    			result = result + a;
    		}
    		a <<= 1;
    		b >>>= 1;
    	}
    	return result;
    } // Note: Received significant help on bitwise operations from Stack Overflow

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
        EquationList test = new EquationList(equation, result, null);
        if (this.list == null) {
        	this.list = new EquationList(equation,result,null);
        } else {
        	EquationList temp = this.list;
        	while (temp.next != null) {
        		temp = temp.next;
        	}
        	temp.next = test;
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
        int i = 0;
        EquationList temp1 = this.list;
        while (temp1 != null) {
        	i = i + 1;
        	temp1 = temp1.next;
        }        
        int j = 0;
        EquationList temp2 = this.list;
        EquationList eqArray[] = new EquationList[i];
        while (j < i) {
        	eqArray[j] = temp2;
        	temp2 = temp2.next;
        	j = j + 1;
        }
        for(int x = i-1; x >= 0; x = x - 1) {
        	System.out.println(eqArray[x].equation + " = " + eqArray[x].result);
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
        int i = 0;
        EquationList temp1 = this.list;
        while (temp1 != null) {
        	i = i + 1;
        	temp1 = temp1.next;
        }        
        int j = 0;
        EquationList temp2 = this.list;
        EquationList eqArray[] = new EquationList[i];
        while (j < i) {
        	eqArray[j] = temp2;
        	temp2 = temp2.next;
        	j = j + 1;
        }
        for(int x = i-1; x >= i-n; x = x-1) {
        	System.out.println(eqArray[x].equation + " = " + eqArray[x].result);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
    	EquationList temp = this.list;
    	while (temp.next.next != null) {
    		temp = temp.next;
    	}
    	temp.next = null;    	
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.list = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    	int sum = 0;
        EquationList temp = this.list;
        if (temp == null) {
        	return 0;
        } else {
        	while (temp != null) {
        		sum = add(temp.result, sum);
        		temp = temp.next;
        	}
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
        EquationList temp = this.list;
        if (temp == null) {
        	return 1;
        } else {
        	while (temp != null) {
        		product = multiply(temp.result, product);
        		temp = temp.next;
        	}
        }
        return product;
    }
}