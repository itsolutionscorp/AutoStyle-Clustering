import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList equationList = new EquationList("", 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
		final int BIT_COUNT = 32;
		int ans = 0, bit_x = 0, bit_y = 0;
		boolean carry = false;
		for (int i = 0; i < BIT_COUNT; i++) {
			bit_x = getBit(x, i);
			bit_y = getBit(y, i);
			if (!carry) {
				if (bit_x == bit_y) {
					if (bit_x == 1)
						carry = true;
				} //otherwise they're different, so turn the bit on
				else
					ans = setBit(ans, i);
			}
			else {
				if (bit_x == bit_y) {
					//set bit and continue carrying
					if (bit_x == 1)
						ans = setBit(ans, i);
					// set bit and end carry
					else{
						ans = setBit(ans, i);
						carry = false;
					}
				} 
			}
		}
        return ans;
    }

	/*returns the ith bit from the right of x */
	public int getBit(int x, int i) {
		int ans = x;
		for (int j = 0; j < i; j++) {
			ans = ans >> 1;
		}
		ans = ans << 31;
		return (ans >>> 31);
	}

	/* Returns x with its ith bit set to 1 */
	public int setBit(int x, int i) {
		int temp = 1;
		for (int j = 0; j < i; j++) {
			temp = temp << 1;
		}
		return temp | x;
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
		boolean negative = false;
		if (x == 0 || y == 0)
			return 0;
		if (x < 0) {
			x = changeSign(x);
			negative = !negative;
		}
		if (y < 0) {
			y = changeSign(y);
			negative = !negative;
		}
		int answer = x;
		for (int i = 1; i < y; i++) {
			answer = add(answer, x);
		}
		return negative ? changeSign(answer) : answer;
    }

	public int changeSign(int x) {
		if (x == 0) 
			return 0;
		return (~(x) + 1);
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
		if (equation.equals(""))
			equationList = new EquationList(equation, result, null);
		equationList = new EquationList(equation, result, equationList);
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
		printHistory(historyLength(equationList));
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
		EquationList head = new EquationList(equationList.equation, equationList.result, equationList.next);
		if (head.next == null) {
			return;
		}
		//EquationList head = head;
		for (int i = 0; i < n; i++) {
			if (head.next == null){
				return;
			}
			System.out.println(head.equation + " = " + head.result);
			head = head.next;

		}
    }    

	public int historyLength(EquationList equationList) {
		EquationList head = new EquationList(equationList.equation, equationList.result, equationList.next);
		int i = 0;
		for (i = 0; head.next != null; i++)
			head = head.next;
		return i;
	}

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
		if (equationList != null)
			equationList = equationList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
		if (equationList != null)
			equationList.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
		EquationList head = new EquationList(equationList.equation, equationList.result, equationList.next);
		if (head.next == null) {
			return 0;
		}
		int sum = 0;
		while (head.next != null){
			sum += head.result;
			head = head.next;
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
		EquationList head = new EquationList(equationList.equation, equationList.result, equationList.next);
		if (head.next == null)
			return 1;
		if (historyLength(head) == 1)
			return head.result;
		int prod = 1;
		while (head.next != null){
			prod *= head.result;
			head = head.next;
		}
        return prod;
    }

}
