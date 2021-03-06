import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int to_carry = x & y;
        int answer = x ^ y;
        while(to_carry != 0) {
            int temp_to_carry = to_carry << 1;
            to_carry = temp_to_carry & answer;
            answer = answer ^ temp_to_carry;
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
        int shift = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                answer = add(answer, (x << shift));
            }
            y = y >>> 1;
            shift = add(shift, 1);
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
        EquationList temp_history = history;
        int length = 0;
        while (temp_history != null) {
            length += 1;
            temp_history = temp_history.next;
        }
        printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp_history = history;
        int i = 0;
        while (i < n) {
            System.out.println(temp_history.equation + " = " + temp_history.result);
            temp_history = temp_history.next;
            i += 1;
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
        EquationList temp_history = history;
        String equation = "";
        int sum = 0;
        while (temp_history.next != null) {
            equation = equation.concat("(" + temp_history.equation + ") + ");
            sum = add(sum, temp_history.result);
            temp_history = temp_history.next;
        }
        equation = equation.concat("(" +temp_history.equation + ")");
        sum = add(sum, temp_history.result);
        history = new EquationList(equation, sum, history);
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList temp_history = history;
        String equation = "";
        int product = 1;
        while (temp_history.next != null) {
            equation = equation.concat("(" +temp_history.equation + ") * ");
            product = multiply(product, temp_history.result);
            temp_history = temp_history.next;
        }
        equation = equation.concat("(" +temp_history.equation + ")");
        product = multiply(product, temp_history.result);
        history = new EquationList(equation, product, history);
        return product;
    }
}