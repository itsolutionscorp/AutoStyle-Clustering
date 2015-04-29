import list.EquationList;

public class Calculator {
    EquationList functionList;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (y == 0){
            return x;
        }
        int a = x ^ y;
        int b = (x & y) << 1;
        // System.out.println("x: "+x);
        // System.out.println("y: "+y);
        // System.out.println("a: "+a);
        // System.out.println("b: "+b);
        return add(a, b);
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    /* Uses the "Russian Peasant" multiplication method, as seen on 
     * http://www.codextream.com/multiply-two-numbers-using-bitwise-operator/
     **/
    public int multiply(int x, int y) {
        // if (x < 0) {
        //     return multiply
        // }
        int product = 0;
        int a = ~ (~ x);
        int b = ~ (~ y);
        while (Math.abs(a) >= 1) {
            if ((a & 1) == 1){
                product = add(product,b);
            }
            a = a >>> 1;
            b = b << 1;
        }
        // System.out.println("x: "+x);
        // System.out.println("y: "+y);
        // System.out.println("a: "+a);
        // System.out.println("b: "+b);
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
        if (functionList == null) {
            functionList = new EquationList(equation,result,null);
        } else {
            functionList = new EquationList(equation,result,functionList);
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
        EquationList cycle = functionList;
        while (cycle != null) {
            System.out.println(cycle.equation+" = "+cycle.result);
            cycle = cycle.next;
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
        int i = 0;
        EquationList cycle = functionList;
        while (i < n) {
            System.out.println(cycle.equation+" = "+cycle.result);
            if (cycle.next == null) {
                break;
            }
            cycle = cycle.next;
            i = add(i,1);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (functionList != null) {
            functionList = functionList.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        functionList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList cycle = functionList;
        while (cycle != null) {
            sum = add(sum,cycle.result);
            cycle = cycle.next;
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
        if (functionList != null) {
            EquationList cycle = functionList;
            int mul = cycle.result;
            cycle = cycle.next;
            while (cycle != null) {
                mul = multiply(mul,cycle.result);
                cycle = cycle.next;
            }
            return mul;
        }    
        return 0;
    }
}