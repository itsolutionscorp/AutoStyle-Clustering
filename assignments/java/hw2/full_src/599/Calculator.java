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
        int answer;
        int carried;
        int temp;

        answer = x ^ y; /*this is what the straight addition gives us*/
        carried = x & y; /*these are the places that will carry over*/

        while (carried != 0){
            carried <<= 1; /*carries things over one spot, because carrying goes to the next place*/
            temp = answer ^ carried; 
            carried = carried & answer; /*repeating the adding step*/
            answer = temp; 
        }

        return answer;
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
        int answer = 0;

        while (y != 0){
            /*check to see if there's something to multiply on this bit*/
            if ((y & 1) != 0){
                answer = add(answer, x);
            }
            x <<= 1; /*multiply what is being added to answer by 2*/
            y >>>= 1; /*move on to the next power of 2*/
        }

        return answer;
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
    EquationList history;

    public void saveEquation(String equation, int result) {
        EquationList a = new EquationList(equation, result, history);
        history = a;

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList a = history;
        int i = 0;

        while(a != null){
            i += 1;
            a = a.next;
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
        EquationList a = history;

        while (n > 0){
            if (a == null){
                n = 0;
            } else {
                System.out.println(a.equation + " = " + Integer.toString(a.result));
                a = a.next;
                n += -1;
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
        EquationList a = history;
        int sum = 0;

        while(a != null){
            sum += a.result;
            a = a.next;
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
        EquationList a = history;
        int prod = 1;

        while(a != null){
            prod = prod * a.result;
            a = a.next;
        }

        return prod;
    }
}