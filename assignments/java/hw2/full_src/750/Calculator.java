import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList calcHistory;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int flip = x ^ y;
        int mask = x & y;
        if (mask == 0){
        } else {
            mask = mask << 1;
            flip = add( flip, mask );
        }
        return flip;
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
        if (x == 0 || y ==0) {
            return 0;
        }
        while (y != 1) {
            int temp = y >>> 1;
            temp = temp << 1;
            if (temp == y) {
            // then y is even and should exchange factor of two
                x = x << 1;
                y = y >>> 1;
            } else {
                // y must be odd, add a principal value and decrement y by 1
                y = add(y,-1);
                return add(x,multiply(x,y));
            }
        }
        return x;
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
            EquationList newResult = new EquationList(equation, result, null);
            if (this.calcHistory == null) {
                this.calcHistory = newResult;
            } else {
                EquationList temp = this.calcHistory;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newResult; // need to find end of chain.
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
        printHistory(Integer.MAX_VALUE);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
       if (this.calcHistory != null && n != 0) {
           // initialize helper values
           String outLine;
           EquationList temp = this.calcHistory;
           EquationList temp2 = this.calcHistory;
        
 
           int histLength = 1; // since this.calcHistory != null
           int printNo = 0; // currently, printed zero times
           while (temp.next != null) {
                temp = temp.next; // will eventually point to last element on list
                histLength = histLength + 1; // will track list length
           }
           
           while (histLength > 0 && printNo < n) {
               
               // print last item on list
                outLine = temp.equation + " = " + temp.result;
                System.out.println(outLine);
                printNo = printNo + 1;

                temp = this.calcHistory; // reset temp for next list traversal
                histLength = histLength - 1;  // decrement "last" by one

                // find new "last" item on list
                for (int i = 1; i < histLength; i++) {
                    temp = temp.next;
               }

           }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (this.calcHistory == null) {
            // do nothing
        } else if (this.calcHistory.next == null) {
            // then history contains one element and should be entirely reset
            this.calcHistory = null;
        } else {
            // then history contains at least two elements
            // find last element, but stay one step back
            EquationList temp = this.calcHistory;
            while (temp.next.next != null) { // look two steps forward
                temp = temp.next; // step forward by one
            }
            // last element is one step forward, annihiliate.
            temp.next = null;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.calcHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int buildingSum = 0;
        EquationList temp = this.calcHistory;
        while (temp != null) {
            buildingSum = add(buildingSum, temp.result);
            temp = temp.next;
        }
        return buildingSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int buildingProduct = 1;
        EquationList temp = this.calcHistory;
        while (temp != null) {
            buildingProduct = multiply(buildingProduct, temp.result);
            temp = temp.next;
        }
        return buildingProduct;
    }
}