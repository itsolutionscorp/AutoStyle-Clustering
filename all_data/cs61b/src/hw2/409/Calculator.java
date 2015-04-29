import list.EquationList;

public class Calculator {

    EquationList equationList;

    // You may wish to add some fields.

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y) {
        if (y == 0){
            return x;
        } else {
            return add(x ^ y, (x & y) << 1);
        }
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
        if (x == 1){
            return y;
        } else if (y == 1){
            return x;
        }

        int final_value = 0;

        while (y != 0){
            if ((y & 1) == 1){
                final_value = add(final_value, x);
            }
            x <<= 1;
            y >>= 1;
        }
        return final_value;
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
    equationList = new EquationList(equation, result, equationList);
    }   

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp_list = equationList;
        while (temp_list != null){
            System.out.println(temp_list.equation + " = " + temp_list.result);
            temp_list = temp_list.next;
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
        int loop = 0;
        EquationList temp_list = equationList;
        while (loop != n){
            System.out.println(temp_list.equation + " = " + temp_list.result);
            temp_list = temp_list.next;
            loop += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationList = equationList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (equationList != null){
            equationList = equationList.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList temp_list = equationList;
        int listSum = 0;
        if (temp_list == null){
            return 0;
        }
        while (temp_list != null){
            listSum += temp_list.result;
            temp_list = temp_list.next;
        }
        return listSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList temp_list = equationList;
        int listSum = 1;
        if (temp_list == null){
            return 0;
        }
        while (temp_list != null){
            listSum = listSum * temp_list.result;
            temp_list = temp_list.next;
        }
        return listSum;
    }
}