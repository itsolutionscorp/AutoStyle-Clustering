import list.EquationList;
public class Calculator {; 
    public EquationList history = null;  
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
        int temp; //temporary value to straight add the carried place 
        int carried = x & y; 
        int add = x ^ y; 
        while (carried != 0) {
            carried <<= 1; //shift what's carried 
            temp = add ^ carried; 
            carried &= add; //adding again 
            add = temp; 
        }
        return add; 
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
        int a = x, b = y;
        int result = 0; 
/*        if (y > x) {
            a = x; 
            b = y; 
        }
        else if ((x < 0) && (y < 0)) {
            int positivex = add(~x, 1); 
            int positivey = add(~y, 1); 
            return multiply(positivex, positivey); 
        } 
        else {
            a = y;
            b = x;
        }

* So the code above was before I knew that the operator >>> existed because I evidently did not pay attention to 
* lecture...
*/
        while (b != 0) {
            if ((b & 1) != 0) {
                result = add(a, result); 
            }
            a <<= 1; 
            b >>>= 1;   
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
        EquationList temp = new EquationList(equation, result, history);
        history = temp; 
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int i = 0; 
        EquationList temp = history; 
        while (temp != null) {
            i += 1; 
            temp = temp.next; 
        } 
        printHistory(i); 
    }
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp = history; 
        if (n == 0) {
            return; 
        } 
        while (n > 0) {
            if (temp == null) {
                return; 
            }
            else {
            System.out.println(temp.equation + " = " + temp.result); 
            temp = temp.next; 
            n -= 1; 
            }
        } 
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = history; 
        int sum = 0;
        if (temp == null) {
            return 0; 
        }
        while (temp != null) {
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
        EquationList temp = history; 
        int sum_prod = 1; 
        if (temp == null) {
            return 1; 
        }
        while (temp != null) {
            sum_prod = multiply(temp.result, sum_prod);
            temp = temp.next;
        }
        return sum_prod; 
    }
}
