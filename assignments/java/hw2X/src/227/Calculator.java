import list.EquationList;

public class Calculator {
    public EquationList el = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    
    public int add (int x, int y){
            if ((x == 0) && (y == 0)){
                return 0;
            }
            else if (x == 0){
                return y;
            }
            else if (y == 0){
                return x;
            } else {
                int sum = x^y;
                int carry = x & y;
                return add(sum, (carry) << 1);
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
        if ((x == 0) || (y == 0)){
            return 0; 
        } 
        else if (x == 1){
            return y; 
        }
        else if (y == 1){
            return x;
        } else {
            int product = 0;
            int mask = 1; 
            int count = 0;
            while (y != 0){
                int check = (y & mask);
                if (check != 0){
                     product += x;
                }
                x = x << 1; 
                y = y >> 1; 
            }
            return product;
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
    public void saveEquation(String equation, int result) {
        el = new EquationList(equation, result, el);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    //Approach 1: Indpen from the other one!
    public void printAllHistory() {
        EquationList pointer = el;
        if (pointer == null){
            return;
        }
        while (pointer.next != null){
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
        }
        System.out.println(pointer.equation + " = " + pointer.result);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int i = 0;
        EquationList pointer_n = el;
        while (i < n){
            System.out.println(pointer_n.equation + " = " + pointer_n.result);
            pointer_n = pointer_n.next;
            i += 1;
        }
    }    
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        int ind = 1;
        while (ind > 0){
            el = el.next;
            ind -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        el = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum_total = 0;
        EquationList arrow_pointer = el;
        while (arrow_pointer != null){
            sum_total += arrow_pointer.result;
            arrow_pointer = arrow_pointer.next;
        }
        return sum_total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList arrow_p = el;
        int product_total = arrow_p.result;
        arrow_p = arrow_p.next;
        while (arrow_p != null){
            product_total *= arrow_p.result;
            arrow_p = arrow_p.next;
        }
        return product_total;
    }
}