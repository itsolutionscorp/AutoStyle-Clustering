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

        int sum = 0;
        int to_add = 0;
        for (int index = 0; index<32; index+=1){
            if (getBit(x, index) != getBit(y, index)){
                sum = setBit(sum, index);
            }
            if (getBit(x, index)==1 && getBit(y, index)==1){
                to_add = add(to_add, setBit(0, index) << 1);
            }
        }
        if (to_add==0){
            return sum;
        }
        return add(to_add, sum);
    }

    public int getBit(int x, int i){
        while (i > 0){
            x = x>>>1;
            i-=1;
        }
        int val = x << 31;
        if (val==0){
            return 0;
        }
        return 1;
    }

    public int setBit(int x, int i){
        int mask = 1 << i;
        return mask | x;
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
        if (x>0 || y>0){
            int counter;
            int other;
            if (x>0){
                counter = x;
                other = y;
            } else {
                counter = y;
                other = x;
            }
            int sum = 0;
            for (int index = 0; index < counter; index++){
                sum = add(sum, other);
            }
            return sum;
        } else {
            x = negate(x);
            y = negate(y);
            return multiply(x, y);
        }
    }

    public int negate(int x){
        int bit = getBit(x, 0);
        int mod = (~x)^1;
        if (bit==0){
           return add(2, mod);
        }
        return mod;
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
        printHistory(size());
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
        if (n > size()){
             printHistory(size());
        } else {
            int index = 0;
            EquationList iterator = history;
            while (index < n){
                System.out.println(iterator.equation + " = " + iterator.result);
                index += 1;
                iterator = iterator.next;
            }
        }
    }

    private int size(){
        int count = 0;
        EquationList iterator = history;
        while (iterator!=null){
            //System.out.println(iterator.equation + " = " + iterator.result);
            count += 1;
            iterator = iterator.next;
        }
        return count;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history!=null) {
            history = history.next;
        }
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
        int sum = 0;
        EquationList iterator = history;
        while (iterator!=null){
            //System.out.println(equation + " = " + result);
            sum += iterator.result;
            iterator = iterator.next;
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
        int product = 1;
        EquationList iterator = history;
        while (iterator!=null){
            //System.out.println(equation + " = " + result);
            product *= iterator.result;
            iterator = iterator.next;
        }
        return product;
    }
}