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

    EquationList list = null;

    public int add(int x, int y) {
        // YOUR CODE HERE
        int end = x ^ y;
        int carry = x & y;
        while (y != 0){
            y = carry << 1;
            carry = end & y;
            end = end ^ y;
        }
        return end;
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
        int end = 0;
        if(x<0){
            x = add(~x,1);
            y = add(~y,1);
        }
        while(x != 0){
            if((x & 1)==1){
                end = add(end,y);
            }
            y <<= 1;
            x >>= 1;
        }
        return end;
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
        EquationList nextlist = new EquationList(equation, result, list);
        list = nextlist;
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
        int counter = 0;
        EquationList temp = list;
        while(temp != null){
            counter += 1;
            temp = temp.next;
        }
        printHistory(counter);

        /*if(list != null){
            System.out.println(list.equation + " = " + list.result);
            if(list.next != null){
                list = list.next;
                printAllHistory();
            }
        }*/
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
        EquationList temp = list;
        for(int a = 0; a<n; a++){
           
            if(temp != null){
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
            }
            else{
                break;
            }
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
        EquationList temp = list;
        int total = 0;
        while(temp != null){
            total += temp.result;
            temp = temp.next;
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
        EquationList temp = list;
        int total = 1;
        while(temp != null){
            total *= temp.result;
            temp = temp.next;
        }
        return total;
        
    }
}