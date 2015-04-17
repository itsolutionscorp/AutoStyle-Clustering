import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList lst = null;

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
        if (x == 0){
            return y;
        }
        if (y == 0) {
            return x;
        }
        // if (x == -y){
        //     return 0;
        // }
        
        int i = x & y;
        i = i << 1;
        // if (i ^ x != i | x) {
        //     int j = i & x;
        //     j = j << 1;
        // }

        // int a = i ^ x;
        int b = x ^ y;
        // int b = add(i, x) ^ y;


        return add(i, b);
        // return b;
    }
    // }
    // else
    //     {return y;}
    // }

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
        if (x == 0 || y == 0) {
            return 0;
        }
        if (y < 0 && x < 0){
            x = add(~ x , 1);
            y = add(~ y, 1);
        }
        if (y < 0 && x > 0){ 
            int a = y;
            int b = x;
            y = b;
            x = a;

        }
        int i = 0;
        int product = 0;
        while (i < y){
            product = add(product, x);
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
        // YOUR CODE HERE


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
        EquationList parse = lst;
        while (parse != null){
            System.out.println(parse.equation + " = " + parse.result);
            parse = parse.next;

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
        // YOUR CODE HERE
        EquationList parse = lst;
        int i = 0;
        while (parse != null && i < n){
            System.out.println(parse.equation + " = " + parse.result);
            parse = parse.next;
            i += 1;

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
        // YOUR CODE HERE
        lst = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList sumlst = lst;
        int sum = 0;
        while (sumlst != null){
            sum = add(sum, sumlst.result);
            sumlst = sumlst.next;
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
        EquationList productlst = lst;
        int product = 1;
        if (productlst == null){
            product = 0;
        }
        while (productlst != null){
            product = multiply(product, productlst.result);
            productlst = productlst.next;
        }
        return product;
        
    }
}