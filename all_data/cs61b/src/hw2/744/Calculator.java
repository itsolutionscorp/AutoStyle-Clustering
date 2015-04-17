import list.EquationList;

public class Calculator {
    public EquationList equationlist = null;
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
        int placeholder = 1;
        int mask = x & y;
        int set = x | y;
        int sum = 0;
        int temp = 0;
        for(int i = 0; i < 32; i++){
            if( (mask & placeholder) == placeholder ) {
                sum = sum;
                temp = placeholder << 1;
            }

            else if ( (set & placeholder) == placeholder){
                if ( (sum & placeholder) == placeholder){
                    sum = sum ^ placeholder;
                    temp = placeholder <<1 ;
                }

                else {
                    sum = sum | placeholder;
                    temp = 0;
                }


            }

            else {
                sum = sum;
                temp = 0;
            }

            sum = sum | temp;

            placeholder = (placeholder << 1);
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

    public int multiply(int x, int y){
        int total = 0;
         while (y != 0){
            if( (y & 1) == 1){
                total = add(x,total);
            }
            y = y >>> 1;
            x = x << 1;
        }
        return total;
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
        equationlist = new EquationList(equation, result, equationlist);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = equationlist;
        while (equationlist != null){
            System.out.println(equationlist.equation + " = " + equationlist.result);
            equationlist = equationlist.next;
        }
        equationlist = temp;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp2 = equationlist;
        for(int i = 0; i < n; i++){
            System.out.println( equationlist.equation + " = " + equationlist.result);
            equationlist = equationlist.next;
        }
        equationlist = temp2;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationlist = equationlist.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationlist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if ( equationlist == null ) return 0;
        int sum = 0;
        EquationList temp3 = equationlist;
        while (equationlist != null){
            sum += equationlist.result;
            equationlist = equationlist.next;
        }
        equationlist = temp3;
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if ( equationlist == null ) return 0;
        int product = 1;
        EquationList temp4 = equationlist;
        while (equationlist != null){
            product *= equationlist.result;
            equationlist = equationlist.next;
        }
        equationlist = temp4;
        return product;
    }
}