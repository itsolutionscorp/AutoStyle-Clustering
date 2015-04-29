import list.EquationList;

public class Calculator {
    EquationList Equations = new EquationList("", 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (y == 0) {
            return x;
        }
        else {
            int part1 = (x ^ y);
            int part2 = ((x & y) << 1);
            return add(part1, part2);
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
        int result = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                result = add(result, x);
            }
            y >>>= 1;
            x <<=  1;
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
        if (Equations.equation.equals("")) {
        Equations.equation = equation;
        Equations.result = result;
        }
        else {
            EquationList addEq = new EquationList(equation, result, Equations);
            Equations = addEq;
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
        if (Equations.equation.equals("")) {
        } else {
        EquationList current = Equations;
        while (current.next != null) {
            System.out.println(current.equation + " = " + current.result);
            current = current.next;
        }
            System.out.println(current.equation + " = " + current.result);
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
        EquationList current = Equations;
            while (n >= 2) {
                System.out.println(current.equation + " = " + current.result);
                current = current.next;
                n = n - 1;
            }        
            if (n == 1) {
                System.out.println(current.equation + " = " + current.result);
            } 
     }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        Equations = Equations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        Equations.equation = "";
        Equations.result = 0;
        Equations.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList current = Equations;
        int result = 0;
        if (Equations.equation.equals("")) {
            return result;
        } else if (current.next == null) {
            return current.result;
        } else {
            while (current.next != null) {
                result = result + current.result;
                current = current.next;
            }
            result = result + current.result;
            return result;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList current = Equations;
        int result = 1;
        if (Equations.equation.equals("")) {
            return result;
        } else if (current.next == null) {
            return current.result;
        } else {
            while (current.next != null) {
                result = result * current.result;
                current = current.next;
            }
            result = result * current.result;
            return result;
        }
    }
}

