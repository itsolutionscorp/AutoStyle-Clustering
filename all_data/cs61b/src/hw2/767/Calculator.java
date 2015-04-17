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
    public EquationList lst = new EquationList("", 0, null);

    public int add(int x, int y) {
        if ((x & y) == 0) {
            return (x ^ y); 
        }
        else {
            return add(x ^ y, (x & y) << 1);
        }
        
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
        int a = x; 
        int b = y; 
        if ((a < 0) && (b < 0)){
            a = -a; 
            b = -b; 
        }
        if (a < 0) {
            a = -a; 
        }
        if (b < 0) {
            b = -b;
        }
        int sum = 0;
        while (b > 0){
            if ((b & 1) != 0) {
                sum = add(sum, a); 
            }
            a = a << 1;
            b = b >> 1; 
        }
        if (((x < 0) && (y > 0)) || ((x > 0) && (y < 0))) {
            return -sum; 
        }
        return sum; 
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
        lst = new EquationList(equation, result, lst); 
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList lst1 = lst; 
        int length = 0; 
        while (lst1.next != null) {
            length += 1; 
            lst1 = lst1.next;
        }
        printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
        EquationList lst1 = lst;
        while (n != 0) {
            System.out.println(lst1.equation + " = " + lst1.result);
            lst1 = lst1.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        lst = lst.next; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        lst = new EquationList("", 0, null); 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
       int sum = 0; 
       EquationList lst2 = lst;
       while (lst2 != null) {
            sum += lst2.result;
            lst2 = lst2.next; 
       }
       return sum; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * /@return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int product = 1; 
        EquationList lst3 = lst;
        while (lst3.next != null) {
            product *= lst3.result;
            lst3 = lst3.next; 
        }
        return product; 
    }    
}
