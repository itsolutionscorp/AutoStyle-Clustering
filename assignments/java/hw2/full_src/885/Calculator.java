import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList tracker = null;
    boolean toggle = true;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        
        int carry = ((x & y) << 1);
        int stay = x ^ y;

        // if (toggle == true){
        //     int trackedx = x;
        //     int trackedy = y;
        //     toggle = false; 
        // }
        
        if ((carry & stay) == 0) {
            // toggle = true;
            // trackedResult = carry | stay;
            // tracker = EquationList(x " + " y, trackedResult, tracker);
            return carry | stay;
        }
        
        else {
            return add(carry,stay);
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

    public int multiply(int x, int y){
        if (x == 0){
            int trackedResult = multiplyHelper(y,x);
            // tracker = EquationList(x " + " y, trackedResult, tracker);
            return trackedResult;
        }
        
        else{
            int trackedResult =  multiplyHelper(x,y);
            // tracker = EquationList(x " + " y, trackedResult, tracker);
            return trackedResult;
        }
    }
    
    public int multiplyHelper (int x, int y) {
        // YOUR CODE HERE
        int counter = 0;
        int bit_count = 32; // can change given representation
        int total = 0;
        int one_or_zero;
        while(counter < bit_count){
            one_or_zero = (x & 1);
            
            if (one_or_zero == 1) {
            total = add(total,y);
            }

            x = x >> 1;
            y = y << 1;
            counter += 1;
        }
        
        return total;
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
        EquationList dummy = tracker;
        tracker = new EquationList(equation, result, dummy);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() { /// maybe have resetting problems <--- remember
        EquationList history = tracker;
        int n = 0;
        while(history != null){
            history = history.next;
            n += 1;
        }
        printHistory(n);
        
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
        int counter = 0;
        EquationList history = tracker;
        while (counter < n){
            if(history != null){
                System.out.print(history.equation + " = " + history.result);
                System.out.println();
                history = history.next;
                counter += 1;
            }

            else{
                System.out.println("OutOfBoundsError: History is not that long");
                counter = n;
            }
        }
    }   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        tracker = tracker.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        tracker = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int total = 0;
        EquationList history = tracker;
        while (history != null){
            total += history.result;
            history = history.next;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int total = 1;
        EquationList history = tracker;
        while (history != null){
            total *= history.result;
            history = history.next;
        }
        return total;
    }
}