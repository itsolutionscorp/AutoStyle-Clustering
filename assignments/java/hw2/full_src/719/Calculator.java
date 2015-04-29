import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int gitbit(int x, int posfromR) {
        x = x << (31-posfromR);
        x = x >> (31);
        if (x<0){
            return -x;
        }
        return x;
    }

    public int gitsumNR(int x, int y) {
        int almostsum = x^y;
        return almostsum;
    }

    public int gitLOs(int x, int y) {
        int lo = x&y;
        return lo;
    }

    public int add(int x, int y) {
        int leftover = gitLOs(x,y) << 1; //moves 1's to next position
        if (leftover != 0) {        
            return add(gitsumNR(x,y), leftover);
        }
        return gitsumNR(x,y);
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
        int total = 0;
        int counter = 0;
        while (y != 0) {
            if (gitbit(y,0) == 1) {
                total = add((x<<counter),total);
            }
            counter++;
            y = y>>>1;
        }
        return total;

        }


        // int currx = x;
        // int curry = y;
        // if (orig==true){
        //     int origx = x;
        //     int origy = y;
        // }
        // if (y==0 || x==0) {
        //     return 0;
        // }

        // if (y<0 && x<0){
        //     x=-x;
        //     y=-y;
        // }
        // if (y<0 && x>0){
        //     y=-y;
        //     boolean neg = true;
        // }
        // if (y>0 && x<0){
        //     x=-x;
        //     boolean neg = true;
        // }
        // orig = false;
        // if (x>=y) {
        // if (y == 1) {
        //     if (neg==true) {
        //     return -currx;
        //     }
        //     return currx;
        // }
        // return multiply(add(currx,origx),add(y,-1));
        // }

        // if (y>=x) {
        // if (x == 1) {
        //     if (neg == true) {
        //     return -curry;
        //     }
        //     return curry;
        // }
        // return multiply(add(x,-1), add(curry,origy));
        // }
        // return 0;
        

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
        // if (history.next == null) {
        history = new EquationList(equation, result, history);
        //}
        // history = new EquationList(equation, result, null);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList copy = history;
        while (copy != null) {
            System.out.print(copy.equation);
            System.out.print(" = ");
            System.out.println(Integer.toString(copy.result));
            copy = copy.next;
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
        EquationList copy = history;
        while (n > 0) {
            System.out.print(copy.equation);
            System.out.print(" = ");
            System.out.println(Integer.toString(copy.result));
            copy = copy.next;
            n -=1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList copy = history;
        if (copy == null) {
            return 0;
        }

        while (copy != null) {
            sum = add(copy.result, sum);
            copy = copy.next;
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
        int prod = 1;
        EquationList copy = history;
        if (copy == null) {
            return 0;
        }

        while (copy != null) {
            prod = multiply(copy.result, prod);
            copy = copy.next;
        }
        return prod;
    }
}