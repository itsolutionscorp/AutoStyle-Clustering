import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList history;

    public Calculator(){
        //Initiate history to null
        history = null;
    }

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
        int answer = 0, carry = 0, xBit, yBit;
        for(int i=0; i < 32; i++){
            xBit = getBit(x, i);
            yBit = getBit(y, i);
            //Carry is 1
            if (carry == 1){ 
                if (xBit == 1 && yBit == 1){
                    answer = setBit(answer, i);
                }
                if (xBit == 0 && yBit == 0){
                    answer = setBit(answer, i);
                    carry = 0;
                }
            //No Carry
            } else {
                if(xBit == 1 && yBit == 1){
                    carry = 1;
                } else if (xBit == 1 || yBit == 1) {
                    answer = setBit(answer, i);
                }
            }
        }
        return answer;
    }

    private int getBit(int x, int i){
        return (x >> i) & 1;
    }

    private int setBit(int x, int i){
        return x | (1 << i);
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
        int answer = 0, xBit;
        for(int i=0; i < 32; i++){
            xBit = getBit(x, i);
            //Left shift for each xBit is One and Add to previous
            if(xBit == 1){
                int addend = y << i;
                answer = add( addend, answer);
                }
            }
        return answer;
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
        // YOUR CODE HERE
        EquationList toPrint = history;
        while (toPrint != null) {
            System.out.println(toPrint.equation + " = " + toPrint.result);
            toPrint = toPrint.next;
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
        EquationList toPrint = history;
        while (n > 0 || toPrint != null) {
            System.out.println(toPrint.equation + " = " + toPrint.result);
            toPrint = toPrint.next;
            n--;
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
     * clearHistory() removes all entries in our history.
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
        int c_sum = 0;
        EquationList toSum = history;
        while (toSum != null) {
            c_sum += toSum.result;
            toSum = toSum.next;
        }
        return c_sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int c_sum = 1;
        EquationList toSum = history;
        while (toSum != null) {
            c_sum *= toSum.result;
            toSum = toSum.next;
        }
        return c_sum;
    }
}