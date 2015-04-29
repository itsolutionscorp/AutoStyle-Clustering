import list.EquationList;

public class Calculator {
    public EquationList histList;
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int getBit(int num, int i) {
        return (num >> i) & 1;
    }

    public int setBit(int num, int i, int set) {
        if (set == 1) {
            return num | (1 << i);
        }
        else {
            return  num & ~(1 << i);
        }
    }

    public int add(int x, int y) {
        while (y != 0) {
            int temp = x ^ y;
            y = x & y;
            x = temp;
            y = y << 1;
        }
        return x;
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
        int temp = 0;
        while (y != 0) {
            for (int i=0; i<32; i++) {
                temp = setBit(temp, i, (getBit(y, 0) & getBit(x, i)));
            }
            result = add(result, temp);
            y = y >>> 1;
            x = x << 1;
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
        if (histList == null) {
            histList = new EquationList(equation, result, null);
        }
        else {
            histList = new EquationList(equation, result, histList);
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
        EquationList curr = histList;
        while (curr != null) {
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
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
        EquationList curr = histList;
        while (n > 0 || curr != null) {
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
            n = add(n, -1);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (histList != null) {
            histList = histList.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        histList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList curr = histList;
        while (curr != null) {
            sum = add(sum, curr.result);
            curr = curr.next;
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
        EquationList curr = histList;
        while (curr != null) {
            product = multiply(product, curr.result);
            curr = curr.next;
        }
        return product;
    }
    
}