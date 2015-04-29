import list.EquationList;

public class Calculator {
    public EquationList history_eq;
    // public boolean has_history = false;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // this we need since its XOR! Will give 1's needed.
        
        int to_keep = x ^ y; 

        /* this we need to get rid of shift to get
         * rid of the ones we need to carry over.
         */
        int to_carry = (x & y) << 1; 
        if (to_carry == 0) {
            return to_keep; // we are done!!! 
        }
        else {
            return add(to_keep , to_carry); // we have to keep shifting!!
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
        int total = 0;
        int a = 1; 

        while (y != 0) {
            if ((y & a) == 0) {
                y = y >>> 1;
                x = x << 1;
            }
            else {
                total = this.add(total,x);
                x = x << 1;
                y = y >>> 1;
            }
        }
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
        // if (has_history == false) {
        //     pointer2.equation = equation;
        //     pointer2.result = result;
        //     pointer2.next = null;
        //     has_history =  true;
        // }
        // else {
        //     pointer2.next = new EquationList(equation, result, null);
        //     pointer2 = pointer2.next;
        // }
        history_eq = new EquationList(equation, result, history_eq);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {


    //     if (pointer == null) {
    //         return;
    //     }
    //     else {
    //         pointer = pointer.next;
    //         printAllHistory();
    //         System.out.println(history_eq.equation + " = " + history_eq.result);
    //     }   
    // }
        EquationList counter = history_eq;
        int size = 0;
        while (counter != null) {
            size +=1;
            counter = counter.next;
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

        // if ( n == 0) {
        //     return;
        // }

        // System.out.println(pointer.equation + " = " + pointer.result);
        
        // pointer = pointer.next;

        // printHistory(n - 1);
        EquationList pointer = history_eq;
        for (int count = 0; count < n; count ++) {
            if (pointer == null) {
                return;
            }
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {

        if (history_eq == null) {
            return;
        }
        history_eq = history_eq.next;

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        EquationList head = history_eq;
        while (head != null) {
            this.undoEquation();
            head = head.next;
        }
  
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {

        int sum_result = 0;
        for (EquationList p = history_eq; p != null; p = p.next) {
            sum_result  = this.add(sum_result, p.result);
        }
        return sum_result;
    }


    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {

        int prod_result = 1;
        for (EquationList q = history_eq; q != null; q = q.next) {
            prod_result = multiply(prod_result, q.result);
        }
        return prod_result;
    }
}



