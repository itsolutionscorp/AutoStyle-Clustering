import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history; 

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
        // int tempx = x;
        // x = x ^ y;
        // y = tempx & y;
        // y = y << 1;


        // while(y != 0) {
        //     tempx = x;
        //     x = x ^ y;
        //     y = (tempx & y) << 1;
        // }

        // return x;

        int tempx = x;

        do {
            tempx = x;
            x = x ^ y;
            y = (tempx & y) << 1;
        } while(y != 0);

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
        // YOUR CODE HERE
        Calculator c = new Calculator();
        int counter = 0; 
        int result = 0;
        int power = 1;

        // while((y < 0 && power >= y) || (power <= y)) {
        //     if ((power & y) != 0)
        //         result = c.add(result, (x << counter));
        //     System.out.println(result + " " + power + " " + counter);
        //     power = power << 1;
        //     counter++;
        // }

        // while(y != 0 && counter < 20) {
        //     if ((power & y) != 0) {
        //         result = c.add(result, (x << counter));
        //         y = (y >>> counter) >>> 1;
        //         y = (y << counter) << 1;
        //     }
        //     power = power << 1;
        //     counter++;
             
        //     System.out.println(result + " " + power + " " + counter + " " + y);
        // }
        // return result;

        if (y < 0 && x < 0) {
            x = c.add(1, ~x);
            y = c.add(1, ~y);
        }
        else if (y < 0) {
            int temp = x;
            x = y;
            y = temp;
        }
        else {

        }

        // while(power <= y) {
        //     if ((power & y) != 0){
        //         if (y < 0)
        //             result = c.add(result, (x << counter));
        //         else
        //             result = c.add(-result, (x << counter));    
        //     }
        //         result = c.add(result, (x << counter));
        //     System.out.println(result + " " + power + " " + counter);
        //     power = power << 1;
        //     counter++;
        // }
        // return result;

        while(y != 0) {
            //System.out.println(result + " " + power + " " + counter + " " + Integer.toBinaryString(y));

            if ((power & y) != 0){
                if (y > 0)
                    result = c.add(result, (x << counter));
                else
                    result = c.add(-result, (x << counter));
                y = (y >>> counter) >>> 1;
                y = (y << counter) << 1;
            }
            power = power << 1;
            counter++;
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
        EquationList e = new EquationList(equation, result, history);
        history = e;
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
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
        EquationList equations = history;
        while(equations != null && n > 0) {
            System.out.println(equations.equation + " = " + equations.result);
            equations = equations.next;
            n--;
        }
            
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList equations = history;
        int sum = 0;
        while(equations != null) {
            sum += equations.result;
            equations = equations.next;
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
        EquationList equations = history;
        int prod = 1;
        while(equations != null) {
            prod *= equations.result;
            equations = equations.next;
        }
        return prod;
    }
}