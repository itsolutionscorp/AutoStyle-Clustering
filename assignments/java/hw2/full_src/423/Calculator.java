import list.EquationList;

public class Calculator {
    
    private EquationList list = null;
    private int listLength = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * 32 bit 2's complement
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        
        int result = 0;
        
        int x_i, y_i, c_i, r_i, c_i_1;

        c_i = 0; // current carry, c_i_1 is the carry value 1 bit over

        for (int i = 0; i <32; i++){ 
            // get the value of the right most bit
            x_i = x&1; 
            y_i = y&1;

            // set the carry and result for the current bit
            c_i_1 = (x_i & y_i) | (x_i & c_i) | (y_i & c_i); 
            r_i = x_i ^ y_i ^ c_i; 

            // set the actual result
            r_i = r_i << i;
            result = result | r_i;

            // for testing
            // System.out.println(" x, y, carry, result_i " + x_i+ "  " + y_i + " " + c_i + " " + r_i_copy + " " + result);

            // go to the next bit in x and y
            x = x >>> 1;
            y = y >>> 1;
            // reassign the carry_i+1 bit
            c_i = c_i_1; 
            
        }

        return result;        
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
        // my code here
        int result = 0;

        for (int i = 0; i<32; i++){
            int x_i = x&1;
            if( x_i ==1 ){
                result = add(result, y<<i);
            }
            
            x = x >>> 1; // shift logical

        }
        return result;
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

        // public EquationList(String equation, int result, EquationList next) 

        EquationList new_listing = new EquationList(equation, result, list);
        list = new_listing;
        listLength +=1;

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(listLength);
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
        EquationList ptr = list;
        for( int i  = 0; i<n; i++){
            System.out.println(ptr.equation + " = " + ptr.result);
            ptr = ptr.next;
            if (ptr == null){
                return;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        list = list.next;
        listLength -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        list = null;
        listLength = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // return 0 if nothing's there
        if (listLength == 0){
            return 0;
        }

        EquationList ptr = list;
        int answer = 0;
        while (ptr != null){
            answer += ptr.result;
            ptr = ptr.next;
        }

        return answer;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // if nothing's there, return 1, per the spec
         if (listLength == 0){
            return 1;
        }

        EquationList ptr = list;
        int answer = 1;

        while (ptr != null){
            answer = answer * ptr.result;
            ptr = ptr.next;
        }

        return answer;
    }
}