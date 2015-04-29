import list.EquationList;

public class Calculator {
    EquationList eqList;
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
        while(y != 0 ){
            int carry  = x & y; 
            x = x ^ y; 
            y = carry << 1;
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
        int returner = 0;
        if(x < y) {
            int temp = y;
            y = x;
            x = temp;
        }
        while(y != 0) {
            if((y & 1) != 0) {
                // System.out.println(x);
                returner = this.add(returner, x);
                // System.out.println(returner);
            }
            x = x << 1;
            y = y >>> 1;
        }
        return returner;
        // int add1 = 0;
        // int add2 = 0;
        // int masker = 1;
        // int returner = 0;
        // while(i < 32) {
        //     if((mult2 & masker) != 0) {
        //         masker = masker << 1;
        //         i++;
        //         continue;
        //     }
        //     adder << 1;
        //     returner = add(returner, adder);
        //     adder = 0;
        //     masker = masker << 1;
        //     i++;

        // }
        // return returner;
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
        // YOUR CODE HERE
        eqList = new EquationList(equation, result, eqList);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        this.printHistory(100000);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList z = this.eqList; 
        while(n >= 1 && z != null) {
            System.out.println(z.equation + " = " + z.result);
            z = z.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        this.eqList = this.eqList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        this.eqList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList s = this.eqList;
        int sum = 0;
        while(s != null) {
            sum += s.result;
            s = s.next;
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
        EquationList s = this.eqList;
        int product = 1;
        while(s != null) {
            product = product * s.result;
            s = s.next;
        }
        return product;
    }
}