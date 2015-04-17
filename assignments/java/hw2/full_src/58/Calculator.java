import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList eqList;
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
	int f = x ^ y;
	int c = (x & y) << 1;
	if (c != 0)
		return add(f, c);
	return f;
	
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
	int t = 0;
	while (y != 0) {
		if ((y & 1) == 1) {
			t += x;
		}
		x <<= 1;
		y >>= 1;
	}
	return t;
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
	if (this.eqList == null) {
		//This is the first equation entered
		this.eqList = new EquationList(equation, result, null);
	}
	else {
		//There are already equations in the list and we need to get to the end of the list and insert it at the end.
		EquationList temp = this.eqList;
		while (temp.next != null) {
			temp = temp.next;
		}
		//Finally link the list to our new EquationList
		temp.next = new EquationList(equation, result, null);
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
        // YOUR CODE HERE
	//We are going to use the print history method however we are going to calculate the amount of history first and pass it as n to the function
	if (this.eqList == null)
		return;
	int n = 1;
	EquationList temp = this.eqList;
	while (temp.next != null) {
		temp = temp.next;
		n++;
	}	
	this.printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printHistoryHelper(int i, EquationList elist) {
	if (i == 0)
		return;
	else {
		System.out.println(elist.equation + " = " + elist.result);
		if (elist.next!= null)
			printHistoryHelper(i - 1, elist.next);
		else {
			return;
		}
	}
    }

    public void printHistory(int n) {
        // YOUR CODE HERE
	// We are going to print n lines of history.
	if (this.eqList == null) 
		return;
	this.printHistoryHelper(n, this.eqList);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquationHelper(int i) {
	//The first thing we need to do is get the length of the list
	int length = 1;
	EquationList temp = this.eqList;
	while (temp.next != null) {
		temp = temp.next;
		length++;
	}
	if (i >= length)
		this.eqList = null;
	else {
		//Reset our temp variable
		temp = this.eqList;
		int n = 1;
		while (length - n > i) {
			temp = temp.next;
			n++;
		}
		temp.next = null;		
	}
    }
    public void undoEquation() {
        // YOUR CODE HERE
	if (this.eqList == null)
		return;
	undoEquationHelper(1);	
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
	if (this.eqList == null)
		return;
	EquationList temp = this.eqList;
	int i = 1;
	while (temp.next != null) {
		temp = temp.next;
		i++;
	}
	this.undoEquationHelper(i);

    }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
    	//Let's look through all of our equations
	if (this.eqList == null)
		return 0;
	EquationList temp = this.eqList;
	int sum = temp.result;
	while (temp.next != null) {
		temp = temp.next;
		sum += temp.result;	
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
    	//Let's look through all of our equations
	if (this.eqList == null)
		return 1;
	EquationList temp = this.eqList;
	int productSum = temp.result;
	while (temp.next != null) {
		temp = temp.next;
		productSum *= temp.result;
	}
	return productSum;
    }
}
