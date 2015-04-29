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
    public EquationList list = null;
    
    public int add(int x, int y) {
        int k = 0;
        int set = 1;
        int result = 0;
        while (x != 0 || y != 0){
            if(((1 & x) & (1 & y)) == 1){
                if (k == 1){
                    result = result | set;
                }
                k = 1;
            } 
            else if((1 & x) != (1 & y)){
                if (k == 0){
                    result = result | set;
                }
            }
            else{
                if (k == 1){
                    result = result | set;
                }
                k = 0;
            }
            x = x >>> 1;
            y = y >>> 1;
            set = set << 1;
        }
        if (k == 1){
            result = result | set;
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
        if (x == 0 || y == 0){
            return 0;
        }
        int saver = 0;
        int result = x;
        while (y != 1){
            if ((y & 1) == 0){
                result = result << 1;
            }
            else{
                saver = add(saver, result);
                result = result << 1;
            }
            y = y >>> 1;
        }
        return result + saver;
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
        list = new EquationList(equation, result, list);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = list;
        while (temp != null){
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
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
        int index = 0;
        EquationList temp = list;
        while (index != n){
            temp = temp.next;
            index = index + 1;
        }
        System.out.println(temp.equation + " = " +temp.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        list = list.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        list = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = list;
        int sum = 0;
        while (temp != null){
            sum += temp.result;
            temp = temp.next;
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
        EquationList temp = list;
        int product = 1;
        while (temp != null){
            product = product * temp.result;
            temp = temp.next;
        }
        return product;
    }
}