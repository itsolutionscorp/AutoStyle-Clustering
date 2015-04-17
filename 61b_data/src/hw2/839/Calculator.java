import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList history;
    
    // Helper function for adding that tracks the bit to carry over
    public int[] addHelper(int a, int b, int mask) {
	int aMasked = a & mask;
	int bMasked = b & mask;
	if (mask == 1) {
	    int[] upshift = {aMasked ^ bMasked, (aMasked & bMasked) << 1};
	    return upshift;
	} else {
	    int[] downshift = addHelper(a, b, mask >>> 1); // downshift[] = [result, carry]
	    int[] upshift = {(aMasked ^ bMasked ^ downshift[1]) | downshift[0], ((aMasked & bMasked) | (aMasked & downshift[1]) | (bMasked & downshift[1])) << 1};
	    return upshift;
	}
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
	return addHelper(x, y, 0x80000000)[0];
    }

    // Helper function for multiplying that keeps track of shifting bits
    public int mulHelper(int a, int b, int shift) {
	int bDigit = (b & (1 << shift)) >>> shift;
	if (shift == 0) {
	    if (bDigit == 0) {
		return bDigit;
	    } else {
		return a;
	    }
	} else if (bDigit == 0) {
	    return mulHelper(a, b, add(shift, -1));
	} else {
	    return add(a << shift, mulHelper(a, b, add(shift, -1)));
	}
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
	return mulHelper(x, y, 31);
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
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	EquationList pointer = history;
	while (pointer != null) {
	    System.out.println(pointer.equation + " = " + pointer.result);
	    pointer = pointer.next;
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
        EquationList pointer = history;
	while (pointer != null && n > 0) {
	    System.out.println(pointer.equation + " = " + pointer.result);
	    pointer = pointer.next;
	    n--;
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
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
	int sum = 0;
	EquationList pointer = history;
	while (pointer != null) {
	    sum += pointer.result;
	    pointer = pointer.next;
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
	EquationList pointer = history;
	while (pointer != null) {
	    product *= pointer.result;
	    pointer = pointer.next;
	}
	return product;
    }
}
