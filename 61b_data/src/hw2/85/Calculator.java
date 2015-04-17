import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eqList;
    int size = 0;


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (y != 0){
            int carryOne = x & y; 
            x = x ^ y;
            y = carryOne << 1;
        }
        return x;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    // &: 1 iff both bits are 1
    // ^: 1 if bits are different, 0 if same
    // >>: moves bits to the right num spaces, top bit copied
    // >>>:  same except top bit is zero
    // <<: move to the left, bottom bit is 0

    public int multiply(int x, int y) {
        int product = 0;
        while(y != 0) {
            if ((y & 1) > 0) { 
                product = add(product, x);
            }  
            x <<= 1;
            y >>>= 1;
        }
    return product;
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
        if (eqList == null) {
            eqList = new EquationList(equation, result, null);
        }
        else {
            eqList = new EquationList(equation, result, eqList);
        }
        size++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList head = eqList;
        while (head != null) {
            System.out.println(head.equation + " = " + head.result);
            head = head.next;
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
        EquationList head = eqList;
        if (n > size) {
            printAllHistory();
        }
        else {
            for (int i = 0; i < n; i++) {
                System.out.println(head.equation + " = " + head.result);
                head = head.next;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (eqList.next != null) {
            eqList = eqList.next;
        }
        else {
            eqList = null;
        }
        size--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = null;
        size = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList head = eqList;
        if (head == null) {
            return 0;
        }
        else {
            while(head != null) {
                sum += head.result;
                head = head.next; 
            }
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
        EquationList head = eqList;
        if (head == null) {
            return 1;
        }
        else {
            while (head != null) {
                product *= head.result;
                head = head.next;
            }
        }
        return product;
    }
}