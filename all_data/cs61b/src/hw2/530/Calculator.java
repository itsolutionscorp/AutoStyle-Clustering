import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = null; 

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
        int remainderCorrectPlace;
        while(y != 0){

            remainderCorrectPlace = (x & y) << 1; 
            x = x ^ y; 
            y = remainderCorrectPlace;

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
        // YOUR CODE HELPER

        if(y < 0 && x < 0){ 
            return multiply(invert(x), invert(y));  
        }
        else if(y < 0){ 
            return multiply(y, x);
        }

        if(y == 0){ 
            return 0;
        }

        int counter = 1;
        int subTotal = x; 
        while(add(counter, counter) < y){ 
            subTotal = subTotal << 1;
            counter = counter << 1; 
        }

        return add(subTotal, multiply(x, add(y, invert(counter))));
    
    }

    private int invert(int z){ 
        return add(~(z), 1);
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
        if (history == null) { 
            history = new EquationList(equation, result, null);
        }
        else { 
            EquationList pointer = history; 
            history = new EquationList(equation, result, null); 
            history.next = pointer; 
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
        // YOUR CODE HERE
        int size = 0; 
        EquationList pointer = history; 
        while(pointer != null){ 
            pointer = pointer.next; 
            size += 1; 
        }
        
        printHistory(size); 

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
        int x = 0; 
        EquationList pointer = history; 
        while(x < n && pointer != null){ 
            System.out.println(pointer.equation + " = " + Integer.toString(pointer.result)); 
            pointer = pointer.next; 
            x = x + 1;
        } 

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.45
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList pointer = history; 
        int sum = 0; 
        while (pointer != null){ 
            sum = sum + pointer.result; 
            pointer = pointer.next; 
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
        EquationList pointer = history; 
        int productSum = 1; 
        while(pointer != null) {
            productSum *= pointer.result;
            pointer = pointer.next; 
        }
        return productSum; 
    }
}