import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqList = new EquationList("string", 1, null);
    public int sizeEqList = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry = (x & y) << 1;
        int xor = x ^ y;
        if (carry != 0) {
            return add(carry, xor);
        }
        return xor;
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

        int xNeg = add(~x,1);
        int yNeg = add(~y,1);

        if (x==0 || y==0) {
            return 0;
        } else if (x<0 && y<0) {
            return helpMul(xNeg, yNeg);
        } else if (x<0 && y>0){ 
            return add(~(helpMul(xNeg, y)), 1);
        } else if (x>0 && y<0){ 
            return add(~(helpMul(x, yNeg)), 1);
        } else {
            return helpMul(x, y);
        }
    }

    public int helpMul(int a, int b) {
        int counter = 1;
        int product = a;
        while (counter < b) {
            counter = add(1, counter);
            product = add(product, a);
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
        EquationList current = eqList;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new EquationList(equation, result, null);
        sizeEqList++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (sizeEqList > 0) {
            printHistory(sizeEqList);
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
        if (n <=0 || n > sizeEqList || sizeEqList <= 0) {
            return;
        }

        EquationList curr = eqList.next;

        while (curr.next != null) {
            curr = curr.next;
        }

        while (n > 0) {
            if (curr == eqList.next) {
                System.out.println(curr.equation + " = " + curr.result);
                n--;
            }
            else {
                EquationList prev = eqList;
                while (prev.next != curr) {
                    prev = prev.next;
                }
                System.out.println(curr.equation + " = " + curr.result);
                curr = prev;
                n--;
            }
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList point = eqList;
        while (point.next.next != null) {
            point = point.next;
        }
        point.next = null;
        sizeEqList--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = new EquationList("string", 1, null);
        sizeEqList = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (sizeEqList <= 0) {
            return 0;
        }
        
        EquationList point = eqList.next;
        int sumAll = point.result;

        while (point.next != null) {
            point = point.next;
            sumAll += point.result;
        }
        return sumAll;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (sizeEqList <= 0) {
            return 1;
        }
        
        EquationList point = eqList.next;
        int productAll = point.result;

        while (point.next != null) {
            point = point.next;
            productAll *= point.result;
        }
        return productAll;
    }
}
