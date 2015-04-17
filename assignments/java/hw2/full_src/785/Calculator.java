import list.EquationList;

public class Calculator {
    EquationList equ = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y) {
        if(y == 0)
        {
            return x;
        }
        else
        {
            int addition = x ^ y;
            int carryOver = (x & y) << 1;
            return add(addition, carryOver);
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
        int answer = 0;
        while(y != 0) 
        {
            if((y & 1) != 0)
            {
                answer = answer + x;
            }
            x = x << 1;
            y = y >>> 1;
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
        equ = new EquationList(equation, result, equ);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList equ1 = equ;
        while(equ1 != null)
        {
            System.out.println(equ1.equation + " = " + equ1.result);
            equ1 = equ1.next;
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
        int count = 0;
        EquationList equ2 = equ;
        while(equ2 != null)
        {
            if(count < n)
            {
                System.out.println(equ2.equation + " = " + equ2.result);
            }
            equ2 = equ2.next;
            count++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
            equ = equ.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while(equ != null)
        {
            equ = equ.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum1 = 0;
        EquationList equ3 = equ;
        while(equ3 != null)
        {
            sum1 += equ3.result;
            equ3 = equ3.next;
        }
        return sum1;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int pro1 = 1;
        EquationList equ4 = equ;
        while(equ4 != null)
        {
            pro1 *= equ4.result;
            equ4 = equ4.next;
        }
        return pro1;

        
    }
}