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
    public int add(int x, int y) {
        // YOUR CODE HERE
/*		if(x == 0){
			return y;
		}else if(y == 0){
			return x;
		}else{
			int z = x & y;
			return add(z, add(z, x ^ y));
		}*/
		int a = x & y;
		int b = x ^ y;
		int c;
		while(a != 0){
			c = a << 1;
			a = c & b;
			b = a ^ b;
		}
		return b;
/*		if(x == 0){
			return y;
		}else if(y == 0){
			return x;
		}else{
		String a = Integer.toBinaryString(x);
		String b = Integer.toBinaryString(y);
		int i = a.length();
		int c, d, e;
		int f = 0;
		String panda = new String();
		while (i > 0 || f = 1){
			if(i != 0){
				d = Integer.parseInt(a[i - 1]);
				e = Integer.parseInt(b[i - 1]);
			}else{
				d = 0;
				e = 0;
			}
			if(f == 0){
				if(d != e){
					c = 1;
				}else{
					c = 0;
					if(d == 1){
						f = 1;
					}
				}
			}else{
				if(d != e){
					c = 0;
				}else{
					c = 1;
					if(d == 0){
						f = 0;
					}
				}
				}
			panda.append(c);
			i++;
			}
		}
		int z = Integer.parseInt(panda, 2);
		return ~z;
		}*/
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
        int z = y;
		int a = 0;
		while(z > 0){
			a = add(a, x);
			z--;
		}
		return a;
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
	EquationList panda;
    public void saveEquation(String equation, int result) {
        panda = new EquationList(equation, result, panda);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
		EquationList tempList = panda;
		while(tempList != null){
			int i = 0;
			printHistory(i);
			tempList = tempList.next;
		}
		return;
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
		EquationList tempList = panda;
		int i = 0;
		while(i != n || tempList != null){
			tempList = tempList.next;
		}
		System.out.println(tempList.equation + " = " + Integer.toString(tempList.result));
		return;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
		if(panda != null){
			panda = panda.next;
		}
		return;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
		while(panda != null){
			undoEquation();
		}
		return;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
		EquationList tempList = panda;
		int i = 0;
		while(tempList != null){
			i = add(i, tempList.result);
			tempList = tempList.next;
		}
        return i;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
		EquationList tempList = panda;
		int i = 1;
		while(tempList != null){
			i = multiply(i, tempList.result);
			tempList = tempList.next;
		}
        return i;
    }
}