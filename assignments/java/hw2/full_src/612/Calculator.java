import list.EquationList;

public class Calculator {

    public EquationList equations;
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
        int and = x & y;
        int carrot = x ^ y;
        if (and == 0) {
            return carrot;
        }
        else {
            while (and != 0) {
                and <<= 1;
                int revised = (carrot ^ and);
                and = and & carrot;
                carrot = revised;
            }
            return carrot;
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
        int product = 0;
        if (((x >>> 31) == 1) && ((y >>> 31) != 1)) {
            return add(~multiply(add(~x, 1), y), 1);
            }
        else if (((x >>> 31) != 1) && ((y >>> 31) == 1)) {
                return add(~multiply(x, add(~y, 1)), 1);
            }
        while (y != 0) {
            if (((x >>> 31) == 1) && ((y >>> 31) == 1)) {
                x = add(~x, 1);
                y = add(~y, 1);
                return multiply(x, y);
            } 
            if ((y & 1) != 0) {
                product = add(product, x);
            }  
            x = x << 1;
            y = y >> 1;
        }
        return product;   
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
        equations = new EquationList(equation, result, equations);
        }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (equations != null) {
            System.out.println(equations.equation + " = " + equations.result);
            equations = equations.next;
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
        int x = 0;
        while (n > x) {
            System.out.println(equations.equation + " = " + equations.result);
            equations = equations.next;
            x += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equations = equations.next;
    }
    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (equations != null) {
            equations = equations.next;
        }

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        while (equations != null) {
            sum = add(equations.result, sum);
            equations = equations.next;
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
        while (equations != null) {
            product = multiply(equations.result, product);
            equations = equations.next;
        }
        return product;
    }
}





