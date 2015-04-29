import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;

    public Calculator(){
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
        int tot = 0;
        int carry = 0;
        int getA = 0;
        int getB = 0;
        for(int pos = 0; pos<32; pos++){
            getA = getBit(x, pos);
            getB = getBit(y, pos);
            tot = setBit(tot, pos, (getA ^ getB ^ carry) | (getA & getB & carry));
            carry = (getA & getB) | (getA & carry) | (getB & carry);
        };
        return tot;
    }

    // public static void main(String... args) {
    //     System.out.println(getBit(2, 1));
    //     System.out.println(3 + ": " + add(1, 2) + "," + Integer.toBinaryString(add(1, 2)));
    // } 

    private int getBit(int x, int i){
        return x >> i & 1;
    }

    private int setBit(int x, int i, int val){
        return (x | (val << i));
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
        if(x == 0 || y == 0){
            return 0;
        }

        int tot = x;
        int start = x;
        boolean yneg = false;

        if(y < 0){
            y = add(~y, 1);
            System.out.println(y);
            yneg = true;
        }
        if (y == 1){
            return tot;
        }

        while(y>1){
            tot = tot << 1;
            y = y - 2;
        }

        if (y == 1){
            tot = add(tot, x);
        }

        if (yneg){
            return add(~tot, 1);
        }
        return tot;
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
        history = new EquationList(equation, result, history)
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(Integer.MAX_VALUE);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        Equationlist curr = history;
        while(n > 0 && curr != null){
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
            n++;
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
        EquationList curr = history;
        while(curr != null){
            sum += curr.result;
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
        EquationList curr = history;
        while(curr != null){
            sum *= curr.result;
        }
        return product;
    }
}