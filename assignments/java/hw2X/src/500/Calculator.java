import list.EquationList;

public class Calculator {

    EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    
    public int add(int x, int y) {
        while (y != 0) {
            int and = x & y;
            x = x ^ y;
            y = (and) << 1;
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

        int answer = 0;
        int i = 0;
        if (y < 0) {
            while (i > y) {
                answer = add(answer, x);
                i -= 1;
            }
            answer = add((~answer), 1);
        } else {
            while (i < y) {
                answer = add(answer, x);
                i += 1;
            }
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
    public void saveEquation(String equation, int result) {
        history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printAllHistory() {  
        if (history != null) {
            EquationList temp = history; 
            while (temp.next!= null) {
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
            }
            System.out.println(temp.equation + " = " + temp.result);
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

        if (history != null) {
            EquationList temp = history;
            while (n > 1 && temp != null) {
                n -= 1;
                temp = temp.next;
            }
            if (temp != null) {
                System.out.println(temp.equation + " = " + temp.result);
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
        int answer = 0;
        while ( temp != null) {
            answer = add(answer, temp.result);
            temp = temp.next;
        }
        return answer;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {

        EquationList temp = history;
        int answer = 1;
        while ( temp != null) {
            answer = multiply(answer, temp.result);
            temp = temp.next;
        }
        return answer;

    }
}