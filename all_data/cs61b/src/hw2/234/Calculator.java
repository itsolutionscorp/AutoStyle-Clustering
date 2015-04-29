import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    private EquationList savedeqs;
    private EquationList sep;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
	int temp;
	while(y != 0) {
	    temp = x;
	    x = x ^ y;
	    y = (temp & y) << 1;
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
	boolean isNegative = false;
       	if ((x < 0) || (y < 0)) {
	    if ((x < 0) && (y < 0)) {
		x = add(~x, 1);
		y = add(~y, 1);
	    } else {
		isNegative = true;
		if (x < 0) {
		    x = add(~x, 1);
		} else {
		    y = add(~y, 1);
		}
	    }
	}

	int orig = x;
	int px;
	int py;
	int indx;
	int answer = 0;
	while (orig > 0) {
	    px = orig;
	    py = y;
	    indx = 0;
	    while (px > 1) {
		py = py << 1;
		px = px >> 1;
		indx = add(indx, 1);
	    }
	    answer = add(answer, py);
	    orig = add(orig, add(~(1 << indx), 1));
	}
	
	if (isNegative)
	    return add(~answer, 1);
	return answer;
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
	if (savedeqs == null) {
	    savedeqs = new EquationList(equation, result, null);
	    sep = savedeqs;
	} else {
	    sep.next = new EquationList(equation, result, null);
	    sep = sep.next;
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
	printHistory(getLength(savedeqs));
    }

    private int getLength(EquationList EL) {
	// returns length of an EquationList
        EquationList ptr = EL;
	if (ptr == null)
	    return 0;
	return 1 + getLength(ptr.next);
    }

    private void printInverted(int length, int n) {
	// prints length amount of equations from savedeqs starting from the
	// most recent equation until n reaches 0
	EquationList ptr = savedeqs;
	int lptr = length;
	while (lptr - 1 > 0) {
	    ptr = ptr.next;
	    lptr -= 1;
	}
	if (ptr != null) {
	    System.out.println(ptr.equation + " = " +
			       Integer.toString(ptr.result));
	}
	if ((length - 1 > 0) && (n - 1 > 0))
	    printInverted(length - 1, n - 1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
	printInverted(getLength(savedeqs), n);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
	int length = getLength(savedeqs);
	EquationList ptr = savedeqs;
	if (length > 1) {
	    while (length - 1 > 1) {
		ptr = ptr.next;
		length -= 1;
	    }
	    ptr.next = null;
	} else
	    savedeqs = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        savedeqs = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
	EquationList ptr = savedeqs;
	int answer = 0;
	if (ptr != null) {
	    while (ptr.next != null) {
		answer = add(answer, ptr.result);
		ptr = ptr.next;
	    }
	    answer = add(answer, ptr.result);
	}
	return answer;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
	EquationList ptr = savedeqs;
	int answer = 1;
	if (ptr != null) {
	    while (ptr.next != null) {
		answer = multiply(answer, ptr.result);
		ptr = ptr.next;
	    }
	    answer = multiply(answer, ptr.result);
	} else
	    return 0;
	return answer;
    }
}
