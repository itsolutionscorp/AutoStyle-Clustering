import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqhistory;
    public Calculator() {
        eqhistory = new EquationList(null, 0, null);
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
        int carryover = x & y;
        int notcarryover = x ^ y;
        carryover = carryover << 1;
        while (carryover != 0) {
            int temp = carryover;
            carryover = carryover & notcarryover;
            notcarryover = temp ^ notcarryover;
            carryover = carryover << 1;
        }
        return  notcarryover;
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
        int remaindersum = 0;

        if (x == 0 || y ==0)
            return 0;
        if (y < 0 && x < 0) {
            x = add(~x, 1);
            y = add(~y, 1);
        } else if (x < 0){
            negative = true;
            x = add(~x, 1);
        } else if (y < 0){
            negative = true;
            y = add(~y, 1);
        }
        int sum = x;
        while (y > 1) {
            int remainder = y & 1;
            y = y >>> 1;
            sum = sum <<1;
            x = sum;
            int remaindertemp;
            while (remainder != 0) {
                remaindertemp = x;
                remainder = remaindertemp & 1;
                remaindersum = add(remaindersum, remaindertemp>>1);

            }
        }
        sum = add(sum, remaindersum);
        if (negative)
            return add(~sum, 1);
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
        EquationList newlist = new EquationList(equation, result, eqhistory);
        eqhistory = newlist;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int icount = 0;
        EquationList temphistory = eqhistory;
        while(temphistory.equation != null) {
            icount++;
            temphistory = temphistory.next;
        }
        printHistory(icount);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temphistory = eqhistory;
        while (n > 0 && temphistory.equation != null) {
            System.out.println(temphistory.equation + " = " + temphistory.result);
            n--;
            temphistory = temphistory.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqhistory = eqhistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqhistory = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temphistory = eqhistory;
        while(temphistory.equation != null) {
            sum += temphistory.result;
            temphistory = temphistory.next;
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
        int product = 1;
        EquationList temphistory = eqhistory;
        while(temphistory.equation != null) {
            product *= temphistory.result;
            temphistory = temphistory.next;
        }
        return product;
    }
}