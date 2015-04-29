import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList historyHead = null;
    
    public int negate(int x){ // negates x using only bitwise operations
        if (x==0) return 0;
        return add(~x, 1);
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
        //System.out.println("1 << 31 >> 31 = "+ ((0x1 << 31) >>> 31 == 0x1));
        int ret = 0;
        boolean carrying = false;
        int rightMostBitX, rightMostBitY, currentBit;
        int leftBit = 1 << 31;
        for (int bitIndex = 31; bitIndex >= 0; bitIndex--){
            rightMostBitX = (x << bitIndex) >>> 31;
            rightMostBitY = (y << bitIndex) >>> 31;
            currentBit = leftBit >>> bitIndex;
            
            if ((rightMostBitX ^ rightMostBitY) == 1){ // XOR
                if (carrying){
                	// Insert zero
                }
                else ret = ret | currentBit; // Insert one
            } 
            else if ((rightMostBitX & rightMostBitY) == 1){ // AND
                if (carrying){
                    ret = ret | currentBit; // Insert one
                }
                else{
                    carrying = true;
                    // Insert zero
                }
            }
            else{ // NAND
                if (carrying){
                    ret = ret | currentBit; // Insert one
                    carrying = false;
                }
                // Insert zero
            }
            
        }
        return ret;
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
        if (x==0 || y==0) return 0;
        boolean xIsNeg = ((x >>> 31)==1);
        boolean yIsNeg = ((y >>> 31)==1);
        if (xIsNeg){ 
        	x = negate(x);
        }
        if (yIsNeg){ 
        	y = negate(y);
        }
        int sum = 0;
        for (int i = 0; i<y; i = add(i,1)){
            sum  = add(sum, x);
        }
        if (xIsNeg != yIsNeg) return negate(sum);
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
        EquationList newHead = new EquationList(equation, result, historyHead);
        historyHead = newHead;
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
        EquationList node = historyHead;
        while (node!=null){
        	System.out.println(node.equation + " = "+ node.result);
        	node = node.next;
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
        EquationList node = historyHead;
        int i = 0;
        while (node!=null && i<n){
        	System.out.println(node.equation + " = "+ node.result);
        	node = node.next;
        	i++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        historyHead = historyHead.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        historyHead = null;
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
        EquationList node = historyHead;
       	while (node!=null){
        	sum+=node.result;
        	node = node.next;
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
        int product = 1;
        EquationList node = historyHead;
       	while (node!=null){
        	product*=node.result;
        	node = node.next;
        }
        return product;
    }
}