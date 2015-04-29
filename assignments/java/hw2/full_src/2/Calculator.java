import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList head;
    private EquationList last;
    private int len = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        int ans = 0;
        int carry = 0;
        int currentBit = 0;
        while (currentBit < 32) {
            int mask = 1 << currentBit;
            int xCurrent = (x & mask) >>> currentBit;
            int yCurrent = (y & mask) >>> currentBit;
            // System.out.println("Bit " + currentBit + ", mask is " + mask);
            // System.out.println(xCurrent + " " + yCurrent);
            int newCarry = xCurrent & yCurrent;
            int set = xCurrent ^ yCurrent;
            if (set == 1 && carry == 1) {
                newCarry = 1;
            }
            set ^= carry;
            ans |= set << currentBit;
            carry = newCarry;
            currentBit++;
        }
        return ans;
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
        // System.out.println("x: " + x + ", y: " + y);
        if (x < 0) {
            return add(~multiply(add(~x, 1), y), 1);
        }
        if (y < 0) {
            return add(~multiply(x, add(~y, 1)), 1);
        }
        if (y == 0) {
            return 0;
        }
        if (y == 1) {
            return x;
        }
        int newY = y - 2;
        return add(x << 1, multiply(x, newY)); 
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
        EquationList current = new EquationList(equation, result, null);
        if (head == null) {
            head = current;
        } else {
            last.next = current;
        }
        last = current;
        len++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
    	printHistory(len);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
    	if (head == null) return;
    	String output = "";
    	int startIndex = len - n; // zero-indexed
    	EquationList iter = head;
    	while (startIndex > 0) {
    		iter = iter.next;
    		startIndex--;
    	}
    	while (iter != null) {
            output = String.format("%s = %d%n", iter.equation, iter.result) + output;    		
    		iter = iter.next;
    	}
    	System.out.println(output.trim());
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
    	if (head == null) return;
    	EquationList iter = head;
    	while (iter.next != last) {
    		iter = iter.next;
    	}
    	last = iter;
    	last.next = null;
    	len--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
    	head = last = null;
    	len = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
    	int sum = 0;
    	EquationList iter = head;
    	while (iter != null) {
    		sum += iter.result;
    		iter = iter.next;
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
    	int prod = 1;
    	EquationList iter = head;
    	while (iter != null) {
    		prod *= iter.result;
    		iter = iter.next;
    	}
        return prod;
    }
}
