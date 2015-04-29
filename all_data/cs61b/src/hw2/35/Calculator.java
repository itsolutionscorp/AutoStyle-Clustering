import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList eList = new EquationList(null, 0, null);

    private int getBit(int x, int i) {
        while (i > 0) {
            x = x >> 1;
            i -= 1;
        }
        return (x & 1);
    }

    private int setBit(int x, int i){
        //goes up to 31
        if (i > 31) {
            return x;
        }
        int adder = 1;
        while (i > 0) {
            adder = adder << 1;
            i -= 1;
        }
        return (x | adder);
    }



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
        int i = 0;
        int carry = 1;
        int tempsum = 0;
        int sum = 0;

        while (carry != 0) {
            carry = 0;
            while (i < 32){
                if ((getBit(x, i) ^ getBit(y, i)) == 1) {
                    tempsum = setBit(tempsum, i);
                }
                else if ((getBit(x, i) & getBit(y, i)) == 1) {
                    carry = setBit(carry, i + 1);
                }
                i += 1;
            }
            x = carry;
            y = tempsum;
            sum = tempsum;
            tempsum = 0;
            i = 0;
        }

        return sum;
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
        int i = 0;
        int product = 0;
        while (i < 32) {
            if (getBit(y, i) == 1) {
                product = add(product, x);}
            x = x << 1;
            i += 1;
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
        eList = new EquationList(equation, result, eList);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = eList;
        while (temp.next != null) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
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
        EquationList temp = eList;
        int i = 0;
        while ((i < n) && (temp.next != null)) {
            System.out.println(temp.equation);
            System.out.println(temp.result);
            temp = temp.next;
            i += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eList = eList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eList = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList temp = eList;
        int sum = 0;
        while (temp.next != null) {
            sum = add(sum, temp.result);
            temp = temp.next;
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
        EquationList temp = eList;
        int product = 1;
        while (temp.next != null) {
            product = multiply(product, temp.result);
            temp = temp.next;
        }
        return product;
    }
}