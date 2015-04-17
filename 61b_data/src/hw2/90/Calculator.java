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
        // YOUR CODE HERE
        if (y== 0){
            return 0;
        }
        else {
           int intermediate = x^y;
            int remainder = (x & y) << 1;
            return add(intermediate, remainder);
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
        // YOUR CODE HERE
        if(y == 0){
            return 0;
        }

        if(y > 0 ){
            return add(x,multiply(x, y-1));
        }

            return -1;
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


    /**
     * Example of how EquationLists work: this list has two stored equations.
     * +-------------------+    +-------------------+
     * | EquationList      |    | EquationList      |
     * +-------------------+    +-------------------+
     * | equation: "1 + 2" |    | equation: "3 * 4" |
     * | result:   3       |    | result:   12      |
     * | next:     --------+--->| next:     null    |
     * +-------------------+    +-------------------+
    **/


    EquationList head = null;

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (head == null){
            head = new EquationList(equation, result,null);
        }
        else{
        EquationList temp = head;
        while (temp.next != null){
            temp  = temp.next;
        }
        temp.next  = new EquationList(equation, result,null);
    }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public static void Inception(EquationList node){
        if (node != null){
            Inception(node.next);
            System.out.println(node.equation  + " = "  + node.result );
        }
    }
    public void printAllHistory() {
        // YOUR CODE HELPER
        Inception(head);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public static void Inception2(EquationList node, int i, int n){
        if (node != null) {
            Inception2(node.next, i+1, n);
            if (i-n > n){
            System.out.println(node.equation  + " = "  + node.result );
            }
        }}
    public void printHistory(int n) {
        // YOUR CODE HERE
        Inception2(head,1, n);
    }


    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList temp  = head;
        if (head.next == null){
            head  = null;
        }
        else{


            while (temp.next.next != null){
                temp  = temp.next;
            }
            temp.next  = null;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        head  = null;
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
        EquationList temp  = head;
        while (temp.next != null){
                sum += temp.result;
                temp  = temp.next;
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
        int product = 0;
        EquationList temp  = head;
        while (temp.next != null){
                product *= temp.result;
                temp  = temp.next;
            }
        return product;
    }
}