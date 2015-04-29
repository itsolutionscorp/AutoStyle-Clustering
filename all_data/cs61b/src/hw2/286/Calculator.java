import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    EquationList history = null;

    public int add(int x, int y) {
			int carryOne = 0;                 //by default, carryOne == 0
			int sum = 0; 											//by default, sum will be 0
			for (int i = 0; i < 32; i++) {		//32 = number of bits in an int
        int curX = ((x >>> i) & 1);     //1 if x has a bit at position i
        int curY = ((y >>> i) & 1);     //same for y
// figures out what this digit of "sum" should be and sets that
// first, checks to see if we're carrying One        
				if (carryOne == 1) {
// 1 if 00 or 11 in that spot, 0 otherwise. Assigns to that digit of sum
// This inverts curX to make our latter equation universal
          int tempX = ((~curX) & 1);
          sum = (sum | ((tempX ^ curY) << i)); 
				} else {
// 0 if 00 or 11, 1 otherwise. Assigns to that digit of sum
          sum = (sum | ((curX ^ curY) << i));
				}

//test to let us know if we have to carryOne
//trip when both are 1 or at least one is 1 and carryOne = 1
				if (((curX & curY) == 1) || (((curX | curY) == 1) && (carryOne == 1))) { 
					carryOne = 1; //tells us we must carry one for next iteration
				} else {
					carryOne = 0;
				}
			}
			return sum;
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
			int sum = 0;
			for (int i = 0; i < 32; i++) {
				if (((y >> i) & 1) == 1) {
          sum = add(sum, x << i);
				}
			}
			return sum;
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
      this.history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
      printHistory(-1);  // i in printHistory will never equal -1
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
      EquationList tracker = history;
			while (i != n) {
				if (tracker == null) {
					return;
				}else{
          System.out.format("%s = %d%n", tracker.equation, tracker.result);
          tracker = tracker.next;
					i++;
				}
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
      EquationList tracker = history;
			int sum = 0;
			while (tracker != null) {
        sum += tracker.result;
				tracker = tracker.next;
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
			EquationList tracker = history;
			int prod = 1;
			while (tracker != null) {
				prod *= tracker.result;
				tracker = tracker.next;
			}
			return prod;
    }
}
