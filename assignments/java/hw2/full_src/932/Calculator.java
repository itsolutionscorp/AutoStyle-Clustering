import list.EquationList;

public class Calculator {
    public EquationList history = new EquationList("blank", 1, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while(y!=0){
            int a = x & y;  
            x = x ^ y;
            y = a << 1; // y = carry
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

    
    public int multiply(int x, int y) {
        int temp = y;
        if(y<0){
            y = add(1, ~ y);
        }
        int i = 0;
        int total = 0;
        while(i<y){
            total = add(x, total);
            i++;
        }
        if(temp<0){
            return add(1, ~total);
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
        if (history.equation == "cleared") {
            history.next = null;
        } else {
        history.next = new EquationList(equation, result, history.next);
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
        EquationList arrow = history;
        int n = 1;
        while (n != 0) {
            if (arrow.next == null) {
                return;
            }
            System.out.println(arrow.next.equation + " = " + arrow.next.result);
            arrow = arrow.next;
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
        EquationList arrow = history;
        while (n != 0) {
            if (arrow.next == null) {
                return;
            }
            System.out.println(arrow.next.equation + " = " + arrow.next.result);
            arrow = arrow.next;
            n -= 1;
        }        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history.next = new EquationList(history.next.next.equation, history.next.next.result, history.next.next.next);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history.equation = "cleared";
        history.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if (history.next == null) {
            return 0;
        }
        EquationList arrow = history;
        while (arrow.next != null) {
            sum += arrow.next.result;
            arrow = arrow.next;
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
        if (history.next == null) {
            return 1;
        }
        EquationList arrow = history;
        while (arrow.next != null) {
            product = product * arrow.next.result;
            arrow = arrow.next;
        }
        return product;
    }
}