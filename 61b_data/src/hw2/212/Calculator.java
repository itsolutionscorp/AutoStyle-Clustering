import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList equationHist = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        /*
        * Carry is either a 1 or 0 depending on the sum of the previous sum.
        * The set operation adds the numbers in the columns.
        * The result is the sum of the rightmost column in the 32 bit integer
        * The for loop iterates through all 32 bits in the integer. 
        */
        int carry = 0;
        int result = 0;
        int sum = 0;

        for(int i = 0; i < 32; i++)
        {
            if(((x & 1) == 1) && ((y & 1) == 1))
        {
                /* result = carry because u want to carry over the last number from the previous
                *  column if it exists (= 1).
                */
                result = carry;
                carry = 1;
            }
            else if((x & 1) != (y & 1))
            {
                if(carry == 1)
                {
                    result = 0;
                    carry = 1;
                }
                else
                {
                    result = 1;
                    carry = 0;
                }
            }
            else
            {
                result = carry;
                carry = 0;
            }
            x = x >>> 1;
            y = y >>> 1;
            /* Sum will contain everything from i.
            *  Because result is left shifted by i (means there will always be a new 
               rightmost column), the new sum will be equal to the old sum from the previous
               column plus the result from the current column. 
            */
            sum = sum | (result << i);
    }               
    return sum;
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

        int max = Math.max(x, y);
        int min = Math.min(x, y);
        int total = 0;
        if (x == 0 || y == 0)
        {
            total = add(min, min);
        }
        // Condition for negative numbers.
        else if(x < 0 || y < 0)
        {   
            for(int i = 0; i < max; i++)
            {
                total = add(total, min);
            }
            if (x < 0 && y < 0)
            {
                /* This for loop works backwards. Goes from a negative
                 * counter to 0. It exits once it hits 0.
                 */
                for(int i = max; i < 0; i++)
                {
                    total = add(total, x);
                }
                /* The next steps flip the bits of the negative number
                 * and add 1 to it to make it positive. 
                 */

                int flippedBits = ~total;
                int positiveNum = add(flippedBits, 1);
                return positiveNum;
            }
            else
            {
                return total;
            }
        }
        else
        {
            for(int i = 0; i < min; i++)
            {
                total = (add(total, max));
            }
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
        /* Create a EquationList instance variable which will act as a pointer
         * (reference) to the equation that is inputted. 
         * To maintain the reference after saveEquation has finished, save the variable
         * in a constructor variable and then call that later with all values in it.
         */
         EquationList equations = new EquationList(equation, result, equationHist);
         equationHist = equations;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // Integer.MAX_VALUE gives the maximum value of an int. 
        printHistory(Integer.MAX_VALUE);

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList history = equationHist;
        for(int i = 0; i <= n; i++)
        {
            if(history == null)
            {
                break;
            }
            else
            {
                System.out.println(history.equation + " = " + history.result);
                history = history.next;       
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationHist = equationHist.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationHist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList sumHist = equationHist;
        int sum = 0;
        while(sumHist != null)
        {
            sum = add(sum, sumHist.result);
            sumHist = sumHist.next;
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
        EquationList prodHist = equationHist;
        int product = 1;
        while(prodHist != null)
        {
            product = multiply(product, prodHist.result);
            prodHist = prodHist.next;
        }
        return product;

    }
}