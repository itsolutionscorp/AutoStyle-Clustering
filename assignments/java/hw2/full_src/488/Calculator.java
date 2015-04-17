import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int res = x ^ y;
        int and = x & y;
        int left = and << 1;
        while(left !=0){
            // System.out.println(left); //remove
            and = left & res;
            res = left ^ res;
            // System.out.println("Res: " + res);
            // System.out.println("and: " + and);
            left = and << 1;
        }
        return res;
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
        int mask = 1;
        int result = 0;
        int term = 0;
        int total = 0;
        if(x==0 || y == 0) {
            return 0;
        }
        if(y < 0) {
            x = add(~x, 1);
            y = add(~y, 1);
        }
        while (total < y) {
            if ((mask & y) == mask) {
                total = add(total, mask);
                term = x;
                for(int temp = mask; temp != 1; temp = temp >>> 1) {
                    term = term << 1;
                }
                result = add(result, term);
            }
            mask = mask << 1;
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
        EquationList newEquation = new EquationList(equation, result, null);
        if (history == null) {
            history = newEquation;
        } else {
            newEquation.next = history;
            history = newEquation;
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
        EquationList p = history;
        while(p != null) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
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
        EquationList p = history;
        for (int count = 1; (count <= n) && (p != null); count++) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
        }
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
        int sum = 0;
        while (history != null) {
            sum += history.result;
            history = history.next;
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
        while (history != null) {
            product *= history.result;
            history = history.next;
        }
        return product;
    }
}