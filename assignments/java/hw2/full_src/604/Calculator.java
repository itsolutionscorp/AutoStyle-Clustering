import list.EquationList;

public class Calculator {
    public EquationList start = new EquationList("start", 1, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int a = x;
        int b = y;
        int val = 0;
        int and = a & b;
        int exclusiveOr = a ^ b;
        while (and != 0) {
            and = and << 1;
            val = exclusiveOr ^ and;
            and = and & exclusiveOr;
            exclusiveOr = val;
        }
        return exclusiveOr;
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
        int a = x;
        int b = y;
        int val = 0;
        while (Math.abs(b) != 0) {
            val = add(a, val);
            b -= 1;
        }
        return val;
    }
    
    // This version of Multiply is gonna be hella slow because it has so many loops

    /*
    public int multiply(int x, int y) {
        int a = x;
        int b = y;
        int val = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                val = add(a, val);
            }
            a = a << 1;
            b = b >> 1;
        }
        return val;
    }
    */
    // This is a different version of multiply made with help from stack overflow
    /*
    In the above case, a will represent the value x multiplied by 2^(i) with i being
    the number of iterations through the while loop.
    b will simultaneously be used to end the while loop and to check each position in
    the binary representation of y.  if ((b & 1) != 0) checks to see if there is a 1
    in the b term and then adds the corresponding value to the sum.
    x * y = sum of x(c_i * 2^i) = sum of (x * 2^i) * c_i
    a = (x * 2^i)
    b = c_i
    */

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
        if (start.equation == "cleared") {
            start.next = null;
        } else {
        start.next = new EquationList(equation, result, start.next);
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
        EquationList pointer = start;
        int n = 1;
        while (n != 0) {
            if (pointer.next == null) {
                return;
            }
            System.out.println(pointer.next.equation + " = " + pointer.next.result);
            pointer = pointer.next;
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
        EquationList pointer = start;
        while (n != 0) {
            if (pointer.next == null) {
                return;
            }
            System.out.println(pointer.next.equation + " = " + pointer.next.result);
            pointer = pointer.next;
            n -= 1;
        }        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        start.next = new EquationList(start.next.next.equation, start.next.next.result, start.next.next.next);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        start.equation = "cleared";
        start.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if (start.next == null) {
            return 0;
        }
        EquationList pointer = start;
        while (pointer.next != null) {
            sum += pointer.next.result;
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
        if (start.next == null) {
            return 1;
        }
        EquationList pointer = start;
        while (pointer.next != null) {
            product = product * pointer.next.result;
            pointer = pointer.next;
        }
        return product;
    }
}