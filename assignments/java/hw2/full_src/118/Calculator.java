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
        int carry = 0;
        int sum = 0;
        int bitSelector = 1;
        for (int i = 0; i<32; i++){
            int a = x & bitSelector; //getting the proper bit
            int b = y & bitSelector;
            sum = sum | (a ^ b ^ carry); //sum if its odd
            carry = (a & b) | (a & carry) | (b & carry); //if the sum of two of them is exactly one then there is a carry
            carry = carry << 1; //if there is a carry then you need to move it over to carry it
            bitSelector = bitSelector<<1; //to incriment to the next bitselector
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
    public int multiply(int x, int y) {
        if (y == 0){
            return 0;
        } else if (y > 0){
            y = add(2,y);
            y = add(y, add(~3,1));
            return add(x,multiply(x, y));
        } else {
            int a = ~x;
            add(a,1);
            return add(-x,multiply(x, add(y,1)));
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
        EquationList yay = new EquationList(equation, result, saved);
        saved = yay;
    }

    EquationList saved;

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temporary = saved;
        while (temporary != null){
            System.out.print(temporary.equation);
            System.out.print(" = ");
            System.out.println(temporary.result);
            temporary = temporary.next;
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
        EquationList temporary = saved;
        while (n != 1){
            temporary = temporary.next;
            n -= 1;
        }
        System.out.print(temporary.equation);
        System.out.print(" = ");
        System.out.println(temporary.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        saved = saved.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        saved = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = saved;
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
        EquationList temp = saved;
        int prod = 1;
        while (temp != null){
            prod *= temp.result;
            temp = temp.next;
        }
        return prod;
    }
}