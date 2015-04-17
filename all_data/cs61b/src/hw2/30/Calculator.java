import list.EquationList;

public class Calculator {
    
    public EquationList stored;
    public int storedLength;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public Calculator() {
        this.stored = new EquationList(null, 0, null);
        this.storedLength = 1;
    }

    public int add(int x, int y) {
        int total = x ^ y;
        int carry = x & y;
        int newCarry;

        while (carry != 0) {
            newCarry = carry << 1;
            carry = total & newCarry;
            total = total ^ newCarry;
        
        }
        return total;
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

        if (x < 0) {
            return -multiply(-x, y);
        } else if (y < 0) {
            return -multiply(x, -y);
        }



        int totalSum = 0;
        int counter = 0;


        while (y != 0) {
            if ((y & 1) != 0) {
                totalSum = add(totalSum, x << counter);
            }

            y = y >> 1;
            counter += 1;
        }

        return totalSum;

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
        if (stored.equation == null) {
            stored.equation = equation;
            stored.result = result;
        } else {
            stored = new EquationList(equation, result, stored);
            storedLength += 1;
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
        printHistory(storedLength + 1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList looper = stored;
        int counter = 0;
        
        while (looper != null && counter < n) {
            System.out.println(looper.equation + " = " + looper.result);
            looper = looper.next;
            counter += 1;
        }
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        stored = stored.next;
        storedLength -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        stored = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history. 
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList looper = stored;
        int totalSum = 0;

        while (looper != null) {
            totalSum += looper.result;
            looper = looper.next;
        }
        
        return totalSum;
            
        

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList looper = stored;
        int totalProd = 1;

        while (looper != null) {
            totalProd = totalProd * looper.result;
            looper = looper.next;
        }
        
        return totalProd;
        
    }
}