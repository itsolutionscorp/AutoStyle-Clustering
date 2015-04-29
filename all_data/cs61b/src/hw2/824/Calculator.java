import list.EquationList;

public class Calculator {
	EquationList mem_list;

	// YOU MAY WISH TO ADD SOME FIELDS

	/**
	 * TASK 2: ADDING WITH BIT OPERATIONS add() is a method which computes the
	 * sum of two integers x and y using only bitwise operators.
	 * 
	 * @param x
	 *            is an integer which is one of two addends
	 * @param y
	 *            is an integer which is the other of the two addends
	 * @return the sum of x and y
	 **/

	/*
	 * public Calculator(EquationList mem_list) { this.mem_list=mem_list; }
	 */

	public int add(int x, int y) {
		while (y != 0) {
			int holder = x & y;
			x = x ^ y;
			y = holder << 1;
		}
		return x;
	}

	/**
	 * TASK 3: MULTIPLYING WITH BIT OPERATIONS multiply() is a method which
	 * computes the product of two integers x and y using only bitwise
	 * operators.
	 * 
	 * @param x
	 *            is an integer which is one of the two numbers to multiply
	 * @param y
	 *            is an integer which is the other of the two numbers to
	 *            multiply
	 * @return the product of x and y
	 **/
	public int multiply(int x, int y) {
		int x_holder = x;
		int y_holder = y;
		int total = 0;
		if (y_holder < 0) {
			if (x_holder < 0) {
				x_holder = ~x_holder + 1;
				y_holder = ~y_holder + 1;
			} else {
				y_holder = x;
				x_holder = y;
			}
		}

		while (y_holder != 0) {
			if ((y_holder & 01) != 0) {
				total = add(total, x_holder);
			}
			x_holder <<= 1;
			y_holder >>= 1;
		}
		return total;
	}

	/**
	 * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
	 * saveEquation() updates calculator history by storing the equation and the
	 * corresponding result. Note: You only need to save equations, not other
	 * commands. See spec for details.
	 * 
	 * @param equation
	 *            is a String representation of the equation, ex. "1 + 2"
	 * @param result
	 *            is an integer corresponding to the result of the equation
	 **/
	public void saveEquation(String equation, int result) {
		mem_list = new EquationList(equation, result, mem_list);
	}

	/**
	 * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
	 * printAllHistory() prints each equation (and its corresponding result),
	 * most recent equation first with one equation per line. Please print in
	 * the following format: Ex "1 + 2 = 3"
	 **/
	public void printAllHistory() {
		while (mem_list != null) {
			printHistory(1);
		}
	}

	/**
	 * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS printHistory()
	 * prints each equation (and its corresponding result), most recent equation
	 * first with one equation per line. A maximum of n equations should be
	 * printed out. Please print in the following format: Ex "1 + 2 = 3"
	 **/
	public void printHistory(int n) {
		while (n > 0) {
			System.out.println(mem_list.equation + " = " + mem_list.result);
			mem_list = mem_list.next;
			n = n - 1;
		}
	}

	/**
	 * TASK 6: CLEAR AND UNDO undoEquation() removes the most recent equation we
	 * saved to our history.
	 **/
	public void undoEquation() {
		mem_list = mem_list.next;
	}

	/**
	 * TASK 6: CLEAR AND UNDO clearHistory() removes all entries in our history.
	 **/
	public void clearHistory() {
		mem_list = null;
	}

	/**
	 * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeSum() computes the
	 * sum over the result of each equation in our history.
	 * 
	 * @return the sum of all of the results in history
	 **/
	public int cumulativeSum() {
		int total = 0;
		if (mem_list == null) {
			return total;
		} else {
			EquationList helper = new EquationList(mem_list.equation,
					mem_list.result, mem_list.next);
			while (helper != null) {
				total = total + helper.result;
				helper = helper.next;
			}
			return total;
		}
	}

	/**
	 * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeProduct() computes
	 * the product over the result of each equation in history.
	 * 
	 * @return the product of all of the results in history
	 **/
	public int cumulativeProduct() {
		int total = 1;
		if (mem_list == null) {
			return total;
		} else {
			EquationList helper = new EquationList(mem_list.equation,
					mem_list.result, mem_list.next);
			while (helper != null) {
				total = total * helper.result;
				helper = helper.next;
			}
			return total;
		}
	}
}
