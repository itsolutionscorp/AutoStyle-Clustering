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
    public int add(int x, int y) {
        // YOUR CODE HERE

        // if they are both ones
        int mask = x&y;

        // if exactly one is one
        int flip = x^y;

        int count = 0;
        int sum = 0;

        while(count<32) {
            //left shift both ones by one
            int savedMask = (mask << 1); 

            // now, if both are ones 
            mask = savedMask&flip;

            // or if one is one
            flip = flip^savedMask;
            count += 1;
        }

        return flip;

  
          

        
        // int count = 0;
        // int sum = 0;
        // while(count<32) {

        //     count += 1;
        // }
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
       // return add(x, y);

        int sum = 0;
        if(y>0) {
            while(y != 0) {
              
                sum = add(x, sum);
              
                y = add(y, -1);
                
            }
          
        }
        else if(y==0) {
            return 0;
        }
        // else if(x<0) {
        //     sum = add(-x, sum);
              
        //     y = add(y, 1);
       // }
        else {
            while(y != 0) {
            //    System.out.println("before: x "+x+"\ny "+y+"\nsum "+sum);
                sum = add(x, sum);
              
                y = add(y, 1);
            }
        }
        return sum;

        // int sum = 0;
        // while(y != 0) {
        //     sum += add(x, sum);
        //     y -= 1;
        // }
        // return 0;

        // int result = 0;
        // for (int i=0; i<y; add(i, 1)) {
        //     result += add(x, result);
        // }
        // return result;
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
        } else {
            history = new EquationList(equation, result, history);
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
        EquationList pointer = history;
        while(pointer != null) {
            System.out.println(pointer.equation + " = " + pointer.result);
        
            pointer = pointer.next;
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
        EquationList pointer = history;
        while(pointer != null && n>0) {
            System.out.println(pointer.equation + " = " + pointer.result);
        
            pointer = pointer.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history.result = 0;
        history.equation = "";
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while(history != null) {
            history.result = 0;
            history. equation = "";
            history = history.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        // if(history==null) {
        //     return 0;
        // }
        
        int cumSum = 0;
        EquationList pointer = history;
        while(pointer != null) {
            cumSum += pointer.result;
            pointer = pointer.next;
        }
        return cumSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int cumProd = 1;
        EquationList pointer = history;
        while(pointer != null) {
            cumProd *= pointer.result;
            pointer = pointer.next;
        }
        return cumProd;
    }
}