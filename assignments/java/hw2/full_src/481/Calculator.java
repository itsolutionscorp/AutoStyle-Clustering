import list.EquationList;

public class Calculator {
    private EquationList savedList;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/




    public int add(int x, int y) {
    
        if (x == 0) {
            return y;
        }
        else if (y == 0) {
            return x;
        }
        
        int total = 0;
        int carry = 0;

        while (x != 0) {
            int sumCurrent = x ^ y;
            carry = x & y;
            y = sumCurrent;
            carry = carry << 1;
            x = carry;
            total = y;
        }


        return total;
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

        int counter = 0;
        int total = 0;
        int multiply = 0;
        while (y != 0) {
            multiply = y & 1;
            y = y >>> 1;
            if (multiply == 1) {
                total = add(total, (x << counter));
            }
            counter++;
        }
        
         return total;

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
        savedList = new EquationList(equation, result, savedList);
    }


    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printAllRecursive(savedList);
    }

    public void printAllRecursive(EquationList allEntries) {
    	if (allEntries != null) {
    		System.out.println(allEntries.equation + " = " + allEntries.result);
    		printAllRecursive(allEntries.next);
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
    	printNRecursive(n, savedList);
    }    

    public void printNRecursive(int n, EquationList allEntries) {
    	if (allEntries != null) {
        	if (n == 1) {
        		System.out.println(allEntries.equation + " = " + allEntries.result);
        	}
        	else {
        		n -= 1;
        		System.out.println(allEntries.equation + " = " + allEntries.result);
        		printNRecursive(n, allEntries.next);
        	}
        }
    }


    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        savedList = savedList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        savedList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;

        EquationList pointerList = savedList;
        
        while (pointerList != null) {
        	sum += pointerList.result;
        	pointerList = pointerList.next;
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

    	EquationList pointerList = savedList;

    	while (pointerList != null) {
    		product *= pointerList.result;
    		pointerList = pointerList.next;
    	}

    	return product;
    }
}
