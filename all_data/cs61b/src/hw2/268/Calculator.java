import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList el = new EquationList("No equations!", 0, null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = x ^ y;
        int carry = x & y;
        while (carry != 0) {
            x = sum;
            y = carry << 1;
            sum = x ^ y;
            carry = x & y;
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
        int ans = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                ans = this.add(ans, x);
            }
            x = x << 1;
            y = y >>> 1;
        }
        return ans;
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
        EquationList temp = this.el;
        if (el.equation == "No equations!") {
            this.el.equation = equation;
            this.el.result = result;
            return;
        }
        this.el = new EquationList(equation, result, temp);
        
        /*
        System.out.println();
        System.out.println("equation = " + equation);
        System.out.println("result = " + result);
        System.out.println("this.equation = " + this.el.equation);
        System.out.println("this.result = " + this.el.result);
        System.out.println("this.next.equation = " + this.el.next.equation);
        System.out.println("this.next.result = " + this.el.next.result);
        */
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (this.el.equation == "No equations!") {
            return;
        }
        EquationList temp = el;
        while (temp.next != null) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
        }
        System.out.println(temp.equation + " = " + temp.result);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (this.el.equation == "No equations!") {
            return;
        }
        EquationList temp = el;
        while (temp.next != null && n > 1) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n = n - 1;
        }
        System.out.println(temp.equation + " = " + temp.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if ( this.el.next != null) {
            this.el = this.el.next;
        } else {
            this.el = new EquationList("No equations!", 0, null);
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.el = new EquationList("No equations!", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if (this.el.equation == "No equations!") {
            return sum;
        }
        EquationList temp = el;
        while (temp.next != null) {
            sum += temp.result;
            temp = temp.next;
        }
        sum += temp.result;
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
        if (this.el.equation == "No equations!") {
            return product;
        }
        EquationList temp = el;
        while (temp.next != null) {
            product *= temp.result;
            temp = temp.next;
        }
        product *= temp.result;
        return product;
    }
}