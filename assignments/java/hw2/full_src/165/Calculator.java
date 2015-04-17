import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eq = new EquationList("0", 0, null);
    int counter = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int result = x ^ y;
        int carry = (x & y) << 1;
        if (carry != 0){
            return add(result, carry);
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
        if (y == 0){
            return 0;
        }
        if (y > 0){
            return add(x, multiply(x, add(y, -1)));
        }
        else if (y < 0){
            return add(~(multiply(x, -y)), 1);
        }
        return 0;

        /*while (y != 0){
            if ((y&1) != 0){
                result = add(result, x);
            }   
            x <<= 1;
            y >>= 1;
        }

        return result;
        

         if (y == 0 || x == 0){
            return 0;
        }
        if (x == 1){
            return y;
        }else {
            return multiply(x >> 1, y << 1);
        }
        */
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
        EquationList temp = new EquationList(equation, result, eq);
        eq = temp;
        counter++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (counter != 0){
            EquationList temp = eq;
            for (int i = 0; i < counter; i++){
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
            }
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
        if (counter != 0){
            EquationList temp = eq;
            for (int i = 0; i < n; i++){
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList temp = eq;
        eq = eq.next;
        temp = null;
        counter--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        EquationList temp;
        for (int i = 0; i < counter; i++){
            temp = eq;
            eq = eq.next;
            temp = null;
        }
        counter = 0;                    
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp;
        temp = eq;
        int sum = 0;
        if (temp == null){
            return sum;
        }
        for (int i = 0; i < counter; i++){
            sum = add(temp.result, sum);
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
        EquationList temp;
        temp = eq;
        int product = 1;
        if (temp == null){
            return product;
        }
        for (int i = 0; i < counter; i++){
            product = multiply(temp.result, product);
            temp = temp.next;
        }
            
        return product;
    }
}
