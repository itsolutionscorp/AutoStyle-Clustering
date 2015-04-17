import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public int i = 0;
    public EquationList stored;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int orSum = x ^ y;
        int andSum = (x & y) << 1;
        int orSum2; 
        int andSum2;
        int total; 

        while (andSum !=0) {
            orSum2 = andSum ^ orSum;

            if ((andSum & orSum) == 0) {
                return orSum2;

            } else {
            andSum = (andSum & orSum) << 1;
            orSum = orSum2 ^ andSum; 
            andSum2 = (andSum & orSum2) << 1;

            if ((andSum2 & orSum) == 0) {
                return (orSum ^ andSum2);

            } else {
            andSum = andSum2;
            }   
        }
    }
     return orSum ^ andSum;
 }


        /*int xcopy = x;
        while ((x & y) != 0) {
            andSum = andSum + x&y << 1;
            x = 1;
            y = 0;
        }
        */

    

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        int i = 1;
        int total = 0;
        int numBits = 32;

        while (i  <= numBits) {
            if (((1 << i) & x) != 0) {
                total = add(total,(y << i));
            }
            i = i + 1; 
        }
        return total;
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
        if (i == 0) {
            new EquationList(equation,result, null);
            stored = new EquationList(equation,result,null);
            i = 1;
        } else {
            new EquationList(equation,result,stored);
            stored = new EquationList(equation,result,stored);
            i = i + 1;
        }
        //EquationList(equation,result,next);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (stored == null) {
        } else {
        EquationList pointer = stored;
        while (pointer.next != null) {
            String Sresult = Integer.toString(pointer.result);
            System.out.println(pointer.equation + " = " + Sresult);
            pointer = pointer.next; 
        } 
        String Sresult = Integer.toString(pointer.result);
        System.out.println(pointer.equation + " = " + Sresult);

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
        int iCounter = i;
        EquationList pointer = stored;
        while (iCounter < 0) {
            pointer = pointer.next;
            iCounter = iCounter - 1;
        } 
        String Sresult = Integer.toString(pointer.result);
        System.out.println(pointer.equation + " = " + Sresult);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        stored = stored.next;
        i = i - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
       stored = null;
       i = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (stored == null) {
            return 0;
        }
        int counter = 0;;
        int total = 0;
        EquationList pointer = stored;
        while (counter < i) {
            total = total + pointer.result; 
            pointer = pointer.next;
            counter++;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (stored == null) {
            return 1;
        }
        int counter = 0;
        int total = 1;
        EquationList pointer = stored;
        while (counter < i) {
            total = total * pointer.result;
            pointer = pointer.next;
            counter++;
        }
        return total;
    }
}