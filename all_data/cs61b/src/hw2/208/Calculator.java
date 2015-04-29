import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = new EquationList("", 0, null);
    //public EquationList history;
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
        // String xBinary = Integer.toBinaryString(x);
        // String yBinary = Integer.toBinaryString(y);
        //while (xBinary.length!= 0 && yBinary.length!= 0) {
            // total += Math.pow(2, xBinary.length-1) * Integer.parseInt(xBinary.substring(0, 1));
            // //shorten x/yBinary's length by truncating one of the numbers.
            // xBinary = xBinary >> 1;
            // yBinary = yBinary >> 1;
        //total = x << 1; 
        //}
        int sum = 0;
        if (y == 0) {
            return x;
        }
        if (x == 0) {
            return y;
        }
        while (x!= 0 && y!= 0) {
            int noCarry = x ^ y;
            int withCarry = (x & y) << 1;
            x = noCarry; 
            y = withCarry;
        }
        return x | y;
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
        int result = 0;
        while (x!=0 && y!=0) {
            if ((y & 01) != 0) { 
                result = add(result, x);
        }
        y = y >> 1;
        x = x << 1;
        }
        return result;
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
        //create an instance variable that holds this information
        //tack it onto the history list created above.
        if (history.equation == "") {
            history.equation = equation;
            history.result = result;
            history.next = null;
        }
        else {
            history = new EquationList(equation, result, history);
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
        // YOUR CODE HERE
        EquationList pointer = history;
        if (pointer == null) {
            //history = new EquationList("", 0, null);
            return;
        }
        if (pointer.equation == "") {
            return;
        }
        while (pointer.next != null) {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
        }
        if (pointer.next == null) {
            System.out.println(pointer.equation + " = " + pointer.result);
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
        EquationList pointer = history;
        int counter = 0;  
        // if (pointer.next == null) {
        //     //history = new EquationList("", 0, null);
        //     printAllHistory();
        //     return;
        //}
        if (pointer.equation == "") {
            return;
        }
        if (history == null) {
            return;
        }
        while (counter < n) {
            if (pointer == null) {
                return;
            }
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
            counter += 1;
        }
        return;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        //move the pointer so history = history.next
        if (history.next == null) {
            history = new EquationList("", 0, null);
            // history.equation = "";
            // history.result = 0;
            // history.next = null;
            //every time after i undo everything and history is null, i cannot make any new statements like 1 + 1.
        }
        else {
            history = history.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history =  new EquationList("", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList pointer = history;
        int sum = 0;
        if (pointer.equation == "") {
            return 0;
        }
        while (pointer != null) {
            sum = add(sum, pointer.result);
            pointer = pointer.next;
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
        EquationList pointer = history;
        int product = 1;
        if (pointer.equation == "") {
            return 1;
        }
        else {
            while (pointer != null) {
            product = multiply(product, pointer.result);
            pointer = pointer.next;
            }
            return product;
        }
    }
}