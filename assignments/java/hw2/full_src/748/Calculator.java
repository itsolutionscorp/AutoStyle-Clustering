import list.EquationList;

public class Calculator {
    EquationList L = new EquationList("You should not see me", 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {	
		int carry = x & y;
		int result = x ^ y;
		int carrymoved;
		while (carry != 0) {
			carrymoved = carry << 1;
			carry = result & carrymoved;
			result = result ^ carrymoved;
		}
		return result;
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
		int result = 0;
		int n = 0;
		int positive = 1;		

        if (y < 0){
			y = add(~ y, 1);
			positive = -1;
		}
		while (n < y){
				result = add(result, x);
				n++;				
			}
		if (positive == -1){
			return add(~ result, 1);		
		}
		
		return result;
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
		EquationList Temp = L;
		EquationList M = new EquationList(equation, result, Temp);
		L = M;
	}

	/*public EquationList flip(EquationList flipping){
		return L;
	}*/

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
		EquationList Temp = L;
        while (Temp.next != null){
			System.out.println(Temp.equation + " = " + Temp.result);
			Temp = Temp.next;
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
        EquationList Temp = L;
		for (int x = 0; x < n; x++){
			System.out.println(Temp.equation + " = " + Temp.result);
			Temp = Temp.next;
		}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList Temp = L;
		L = Temp.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        L = new EquationList("You should not see me", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList Temp = L;
		int answer = 0;
        while (Temp.next != null){
			answer += Temp.result;
			Temp = Temp.next;
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
        EquationList Temp = L;
		int answer = 1;
        while (Temp.next != null){
			answer *= Temp.result;
			Temp = Temp.next;
		}
		return answer;
    }
}
