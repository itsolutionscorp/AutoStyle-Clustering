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
        int a = x ^ y;
        int b = x & y;
        b = b << 1;
        if ((a | b) == (a ^ b)) {
            return a | b;
        } else {
            return add(a, b);
        }
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
        if ((x < 0) & (y < 0)) {
            return multiply(-x, -y);
        } else if ((x > 0) & (y < 0)) {
            return multiply(y, x);
        } else {
        int power = 1;
        int value = 0;
        while (power < 1073741824) {
            if ((power & y) == power) {
                int counter = power;
                while (counter > 0) {
                    value = add(value, x);
                    counter = add(counter, -1);
                    }
                power = add(power, power);
            } else {
                power = add(power, power);
            }
        }
        return value;
    }
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

    public EquationList elist = new EquationList(null, 0, null);

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        EquationList helperlist = elist;
        if (elist.equation == null) {
            elist.equation = equation;
            elist.result = result;
            elist.next = null;
        } else {
            while (helperlist.next != null) {
                helperlist = helperlist.next;
            }  
            helperlist.next = new EquationList(equation, result, null);
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
        // YOUR CODE HERE
        if (elist.equation == null) {
        } else {
            helper(elist);
    }
}
    public void helper(EquationList helperlist) {
        if (helperlist.next == null) {
            System.out.println(helperlist.equation + " = " + helperlist.result);
        } else {
            helper(helperlist.next);
            System.out.println(helperlist.equation + " = " + helperlist.result);
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
        // YOUR CODE HERE
        if (elist.equation == null) {
        } else {
        EquationList helperlist = elist;
        int counter = 1;
        while (helperlist.next != null) {
            counter = counter + 1;
            helperlist = helperlist.next;
        }
        int difference = counter - n;
        helperlist = elist;
        while (difference >= 1) {
            helperlist = helperlist.next;
            difference -= 1;
        }
        System.out.println(helperlist.equation + " = " + helperlist.result);
    }
}

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList helperlist = elist;
        if (elist.next == null) {
            elist = new EquationList(null, 0, null);;
        } else {while (helperlist.next.next != null) {
            helperlist = helperlist.next;
        }
        helperlist.next = null;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
    elist = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList helperlist = elist;
        int sum = 0;
        while (helperlist != null) {
            sum += helperlist.result;
            helperlist = helperlist.next;
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
        EquationList helperlist = elist;
        int product = 1;
        while (helperlist != null) {
            product *= helperlist.result;
            helperlist = helperlist.next;
        }
        return product;
    }
}