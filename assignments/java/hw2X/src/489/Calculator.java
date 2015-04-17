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
    public int add(int x, int y) {

        if((x & y) << 1 == 0){
            return (x^y);
        }

        if((x ^ y) == 0){
            return ((x & y) << 1);
        }
        
        else{
            return add((x & y) << 1, (x ^ y));
        }

    }

    // public static void main(String[] args){
    //     Calculator test = new Calculator();
    //     int res = test.add(-37, 38);
    //     System.out.println(res);
    // }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {

        if( y == 0){
            return 0;
        }
        if( x == 0){
            return 0;
        }
        if(y == 1){
            return x;
        }
        if(x == 1){
            return y;
        }
        if(x < 0 && y > 0){
            return multiply(add(1, ~x), add(1, ~y));
        }
        if(x > 0 && y < 0){
            return add(add(1, ~x), multiply(x, add(y, 1)));
        }
        if(x < 0 && y < 0){
            return multiply(add(1, ~x),add(1, ~y));
        }
        else{
            return add(x, multiply(x, add(y, -1)));
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

    public EquationList stored;

    public void saveEquation(String equation, int result) {

        EquationList p = stored;
 
        if(p != null){
            stored = new EquationList(equation, result, stored);
        }
        else{
            stored = new EquationList(equation, result, null);
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
        int n = 0;
        EquationList p = stored;
        while (p != null){
            n += 1;
            p = p.next;
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
        EquationList pointer = stored;
            for(int i = 0; i < n; i++){
                System.out.println(pointer.equation + " = " + pointer.result); 
                pointer = pointer.next; 
            }   
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        stored = stored.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        stored = null;
    }


    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = stored;
        if (stored != null){
            int ret = 0;
            while(pointer != null){
                ret = add(ret, pointer.result);
                pointer = pointer.next;
            }
            return ret;
        }
        else{
            return 0;
        }
    }

    // public static void main(String[] args){
    //     Calculator test = new Calculator();
    //     test.saveEquation("1 + 1", 2);
    //     test.saveEquation("1 + 2", 3);
    //     test.saveEquation("1 + 3", 4);
    //     test.saveEquation("1 + 4", 5);
    //     test.printAllHistory();
    //     System.out.println("sum");
    //     System.out.println(test.cumulativeSum());
    //     System.out.println("product");
    //     System.out.println(test.cumulativeProduct());
    //     System.out.println("cleared");
    //     test.clearHistory();
    //     System.out.println("sum");
    //     System.out.println(test.cumulativeSum());
    //     System.out.println("product");
    //     System.out.println(test.cumulativeProduct());
    // }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList pointer = stored;

        if(stored != null){
            int ret = 1;
            while(pointer != null){
                ret = multiply(ret, pointer.result);
                pointer = pointer.next;
            }
            return ret;
        }
        else{
            return 0;
        }
    }
}