import list.EquationList;

public class Calculator {	
	private EquationList ori;
	
	public static void main(String[] args){	
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
		int b = 1;
		int z = 0;
		while (b != 0) {
			z = x ^ y;
			b = x & y;
			int a = b << 1;
			x = z;
			y = a;
		}
		return z;
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
		int i = 0;
		int newx = 0;
		int finalans = 0;
		int copyy = y;
			while (copyy != 0) {
				if (copyy << 31 != 0) {
					newx = x << i;	
				}
				else {
					newx = 0;
				}
			copyy = copyy >>> 1;
			i = i + 1;
			Calculator addresult = new Calculator();
			finalans = addresult.add(newx, finalans);	
			}
		return finalans;
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
		this.ori = new EquationList(equation, result, this.ori);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
		EquationList temp = ori;
		if (temp != null) {
			while (temp.next != null) {
				System.out.println(temp.equation + " = " + temp.result);
				temp = temp.next;
			}
			if (temp.next == null) {
				System.out.println(temp.equation + " = " + temp.result);
			}
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
		EquationList j = ori;
		for (int i = 0; i < n && j != null; i = i + 1) {
			System.out.println(j.equation + " = " + j.result);
			j = j.next;
		}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
		if (ori != null) {
			ori = ori.next;
		}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        ori = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
		int ini = 0;
		EquationList pointer = ori;
        while (pointer != null) {
			int t = pointer.result + ini;
			ini = t;
			pointer = pointer.next;
		}		
        return ini;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int ini = 1;
		EquationList pointer = ori;
        while (pointer != null) {
			int t = pointer.result * ini;
			ini = t;
			pointer = pointer.next;
		}		
        return ini;
    }
}