import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList hist;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry = x & y;
        int answer = x ^ y;
        int temp;
        while (carry != 0) {
            carry = (carry << 1);
            temp = answer ^ carry;
            carry = answer & carry;
            answer = temp;          
        }
        return answer;
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
        if ((x == 0 )||( y == 0)) {
            return 0;
        }
        int ans = 0;
        while(y != 0) {
            int temp = y & 1;
            if (temp == 1) {
                ans = this.add(x, ans);
            }
            y >>>= 1;
            x <<= 1;
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
        if (this.hist != null) {
            EquationList s = new EquationList(equation, result, this.hist);
            this.hist = s;          
        } else {
            this.hist = new EquationList(equation, result, this.hist);
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
        EquationList temp = this.hist;
        if (temp == null) {
            return;
        }
        if (temp.next == null) {
            System.out.format("%s = %d" + "%n", temp.equation, temp.result);
            return;            
        }
        while (temp != null) {
            System.out.format("%s = %d" + "%n", temp.equation, temp.result);
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
        EquationList temp = this.hist;
        int i = 0;
        while ((i < n)&&(temp != null)) {
            System.out.format("%s = %d" + "%n", temp.equation, temp.result);
            temp = temp.next;
            i = i + 1;
        }
    }   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        this.hist = this.hist.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.hist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = this.hist;
        int sum = 0;
        while (temp != null) {
            sum = this.add(sum, temp.result);
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
        EquationList temp = this.hist;
        if (temp == null) {
            return 0;
        }
        int prod = 1;
        while (temp != null) {
            prod = this.multiply(prod, temp.result);
            temp = temp.next;
        }
        return prod;
    }
}