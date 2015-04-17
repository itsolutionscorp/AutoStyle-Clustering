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
        int n = 0;
        int bitmask;
        int sum = y;
        boolean carry = false;

        while( n < 32 ){
            bitmask = 1 << n;


            int x_nth = x & bitmask; //the nth digit, base 2, of x, isolated
            int y_nth = y & bitmask; // same for y



            if(x_nth == y_nth){

                if(x_nth == 0){ //they're both 0
                    if(carry){ // if we're carrying, make it 1
                        sum = sum | bitmask;
                        carry = false;
                    }
                }else{ //they're both 1
                    if(!carry){ // if we're not carrying, make it 0
                        sum = sum & (~bitmask); //it becomes 0, and we carry
                    }
                    
                    carry = true;
                }

            }else{
                if(carry){
                    sum = sum & (~bitmask);
                }else{
                    sum = sum | bitmask; //it becomes 1
                }
            }

            n++;

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

        int n = 0;
        int bitmask;
        int product = 0;

        while( n < 32 ){
            bitmask = 1 << n;

            if(!((y & bitmask) == 0)){ //it's 1
                product = add(product, x<<n);
            }

            n++;
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
        history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList ptr = history;
        while(ptr != null){

            System.out.println(ptr.equation + " = " + ptr.result);

            ptr = ptr.next;
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
        EquationList ptr = history;
        int depth = 0;
        while(ptr != null && depth < n){

            System.out.println(ptr.equation + " = " + ptr.result);

            ptr = ptr.next;
            depth++;
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
        EquationList ptr = history;
        while(ptr != null){

            sum += ptr.result;

            ptr = ptr.next;
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
        int product = 1;
        EquationList ptr = history;
        while(ptr != null){

            product *= ptr.result;

            ptr = ptr.next;
        }

        return product;
    }
}