import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList storage = null;
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

        int sum = 0;
        int carryOne = 0;

        for(int i = 0; i < 32; i++) 
        {
            int flip = 1 << i; //way to put a one in the place you want it to be
            if( ((x & flip) == flip) && ((y & flip) == flip) ) //if both are 1s
            {
                if(carryOne == 1) { //if already has a carry, becomes 1 + 1 + 1 = 11 so change col to 1
                    sum = sum | flip; 
                }
                else //1 + 1 = 10 so a 1 gets carried and 0 is left so col doesn't change to 1
                {    carryOne = 1;    }
            }

            else if ( ((x & flip) == 0)  && ((y & flip) == 0) ) { //if both are 0s
                if(carryOne == 1) { //check if there's a carry stored
                    sum = sum | flip; //adds in 1 at that column
                    carryOne = 0; //resets the carry
                }
            }

            else { //if one of them is 1 and other is 0
                if(carryOne == 0) //if no carry, then put 1 in the col
                {    sum = sum | flip;    } 
            }
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
        int sum = 0;
        int value = 0;
        for(int i = 0; i < 32; i++) {
            int two_pwr = 1 << i;
            if( (y & two_pwr) == two_pwr) {
                value = x << i; 
                sum = add(sum, value);
            } 
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
        // YOUR CODE HERE
        storage = new EquationList(equation, result, storage); 
    }

    // public String getFront(EquationList eqn) {
    //     return storage.equation;
    // }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList show = storage;
        while(show != null) {
            System.out.println(show.equation + " = " + show.result);
            show = show.next;
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
        EquationList show = storage; 
        for(int i = 1; i <= n; i++) {
            if(show != null) {
                System.out.println(show.equation + " = " + show.result);
                show = show.next;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        storage = storage.next; //errors if nothing is in there in the first
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        storage = null;
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
        EquationList check = storage;
        if(check == null) {
            return 0;    
        }
        while(check != null) {
            sum += check.result;
            check = check.next;
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
        int prod = 1;
        EquationList check = storage;
        if(check == null) {
            return 1;
        }
        while(check != null) {
            prod *= check.result;
            check = check.next;
        }
        return prod;
    }
}