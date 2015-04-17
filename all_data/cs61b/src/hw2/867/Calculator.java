import list.EquationList;

public class Calculator {
    EquationList list; // Where list is the master list of the history
    EquationList copy; // Where copy is a copy of list after all equations have been added 
                       // the history


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
            int isone = (x & y);
            x = x ^ y; 
            y = isone << 1;
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
        // YOUR CODE HERE
//================================================================================================
        // Determines if final product should be positive or negative
        boolean isNegative = false;
        if (x < 0) {
            if (y < 0) {
                isNegative = false;
            }
            else {
                isNegative = true;
            }
        } 
        else if (y<0) {
                isNegative = true;
        }
//================================================================================================

        x = Math.abs(x);  // Having already dealt with the final sign of the product, we can 
        y = Math.abs(y);  // perform multiplication without accounting for individual signs

        int product = 0;
        int counter = 0;
        while (counter < y) {
            product = add(product, x);
            counter += 1;
        }
        if (isNegative == true) {
            product = add(~product, 1);//If product predetermined to be negative, negates product
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
        if(list == null){
            list = new EquationList(equation, result, null); //Creates initial instance of list
        }
        else{
            list = new EquationList(equation, result, list); //Adds each couplet of equation and
        }                                                    //result to previous list
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
        printHistory(-1);
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
        copy = list;  // Creates copy of list so that x = x.next does not effect original list
        while (n!=0 & copy!=null){
            System.out.println(copy.equation + " = " + copy.result);
            copy = copy.next;
            n-=1;
        }    
    }
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if(list != null){
            list = list.next;
        }

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        list = null;
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
        copy = list;
        while (copy!=null){
            sum += copy.result;
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
        // YOUR CODE HERE
        int product = 1; //Creates base to which loop can perform multiplication
        copy = list;
        while (copy != null) {
            product *= copy.result;
            copy = copy.next;
        }
        return product;
    }
}