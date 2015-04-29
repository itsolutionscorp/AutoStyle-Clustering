import list.EquationList;

public class Calculator {
    EquationList eql = new EquationList("There's no calcualiton history!", 0, null);
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
        while (y != 0) {
            int carry = x & y;
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
        int temp = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                temp = this.add(temp,x);
            }
            x = x << 1;
            y = y >>>1;
        }
        return temp;
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

    //need pay attention on THIS
    public void saveEquation(String equation, int result) {
        if(eql.equation == "There's no calcualiton history!") {
            eql.equation = equation;
            eql.result = result;
            return;
        }
        eql = new EquationList(equation, result, eql);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (eql.equation == "There's no calcualiton history!") {
            return;
        }
        EquationList scan = eql;
        while (scan.next != null) {
            System.out.println(scan.equation + " = " + scan.result);
            scan = scan.next;
        }
        System.out.println(scan.equation + " = " + scan.result);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (eql.equation == "There's no calcualiton history!") {
            return;
        }
        EquationList scan = eql;
        while(n != 1) {
            scan = scan.next;
            n = n-1;
        }
        System.out.println(scan.equation + " = " + scan.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (eql.next == null) {
            eql = new EquationList("There's no calcualiton history!", 0, null);
        } else {
            eql = eql.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eql = new EquationList("There's no calcualiton history!", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if(eql.equation == "There's no calcualiton history!") {
            return sum;
        } 
        EquationList scan = eql;
        while(scan.next != null) {
            sum = this.add(sum, scan.result);
            scan = scan.next;
        }
        sum = this.add(sum, scan.result);
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int prod = 1;
        if(eql.equation == "There's no calcualiton history!") {
            return prod;
        } 
        EquationList scan = eql;
        while(scan.next != null) {
            prod = this.multiply(prod, scan.result);
            scan = scan.next;
        }
        prod = this.multiply(prod, scan.result);
        return prod;
    }
}