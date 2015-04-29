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

    // OR + AND = sum!
    // XOR + AND = OR
    // XOR + 2*AND = sum!

    //base case: 2*AND = 0; therefore answer is XOR.
    //Need to change x & y...
        //tried to change x & y to OR and AND, but sometimes yields the same x & y.
        //then changed x & y to XOR and 2*AND... that seems to work better.
            //So, question. Can x & y ever be exactly half of x or y?
                //for that to happen, x & y >> 1, basically.
                //try: abcdefg0 & hijklmno = pqrstuv0...
                //           abcdefg0 >> 1 = aabcdefg...
                        //so, g = 0... (so one of the numbers must be divisible by 4)
                        //but... uhm. 

    public int add(int x, int y) {
        while ((x & y) != 0) {
            int temp = x;
            x = (x & y) << 1;
            y = temp ^ y;
        }
        return (x ^ y);
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/

    //for y % 2 == 0.
    //(x)(y) = (2x)(y/2)
    //(x)(y) = (x << 1)(y >> 1) until y = 1, at which point return x.
    //Need to track a remainder, and then multiply by that.

    //let y = y2 + yr (y2 = highest power of y2; yr = remainder of y2.)
      //therefore, yr = y - y2.
      // y = y2 when yr = 0. y2 is y >> 1, right?
        // I need to accurately calculate y_rem.
      // y = y >> 1 means y // 2^1.
        //y_rem is y - y >> 1. subtraction is y + (-(y >> 1))
    public int multiply(int x, int y) {
        if (y == 0)
            return 0;
        if (y < 0)
            return multiply(negate(x), negate(y));
        if (y == 1)
            return x;

        int y_rem = 0;
        int x_orig = x;
        
        x = x << 1;
        y_rem = add(y, negate((y >> 1) << 1));
        y = y >> 1;
        return add(multiply(x, y), multiply(x_orig, y_rem));
    }

    public int negate(int x) {
        return add(~x, 1);
    }
      
/*    public int multiply(int x, int y) {
        if ((x == 0) || (y == 0)) {
            return 0;
        }
        else if (x == 1) {
            return y;
        }
        else if (x < 0) {
            return add(~(multiply(add(~x, 1), y)), 1);
        }
        else if (x > y) {
            return multiply(y, x);
        }
        else {
            int l = (multiply(add(x, -1), y));
            return add(l, y);
        }
    }*/

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/

    EquationList equationList = null;

    public void saveEquation(String equation, int result) {
        if (equationList == null) {
            equationList = new EquationList(equation, result, null);
        }
        else {
            EquationList recent = new EquationList(equation, result, equationList);
            equationList = recent;
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
        EquationList pointer = equationList;
        int n = 0;
        while (pointer != null) {
            n = n + 1;
            pointer = pointer.next;
        }
//      System.out.println(n + " equation(s).");
        printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList pointer = equationList;
        while (n > 0) {
            System.out.println(pointer.equation + " = " + pointer.result);
            n = n - 1;
            pointer = pointer.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationList = equationList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList pointer = equationList;
        while (pointer != null) {
            sum = sum + pointer.result;
            pointer = pointer.next;
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
        EquationList pointer = equationList;
        while (pointer != null) {
            product = product * pointer.result;
            pointer = pointer.next;
        }
        return product;    
    }
}