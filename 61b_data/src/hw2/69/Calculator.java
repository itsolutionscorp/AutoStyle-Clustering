import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = new EquationList("None", 0, null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {

        //YOUR CODE HERE
        int marker = 1;
        int a;
        int b;
        boolean carryover = false;
        int i = 0;
        int digitsum = 0;
        if (x == 0 || y == 0) {
            digitsum = x | y;
        }
        while (i < 32) {
            a = (x & marker);
            b = (y & marker);

            if (carryover) {
                digitsum = digitsum | (marker);
            }
            //A and b does't necessarily = 0
            if (a == b && a != 0) {
                carryover = true;
                digitsum = ((a ^ b) ^ digitsum);
            }
            else if (a != b && carryover) {
                carryover = true;
                digitsum = ((a ^ b) ^ digitsum);
            }
            else {
                carryover = false;
                digitsum = ((a ^ b) | digitsum);
            }
            
            marker = marker << 1;
            i += 1;

        }
        
        return digitsum;
    }

    //     int init = 0;
    //     int incre = 1;

    //     while (init != y) {
            
    //         if ((x & incre) == 0){
    //             x = (x | incre);

    //         }
    //         else {
    //             x = (((x << incre) | incre) ^ x);
    //         }

    //         if ((init & incre) == 0){
    //             init = (init | incre);

    //         }
    //         else {
    //             init = (((init << incre) | incre) ^ init);
    //         }
    //         }
        
    //     return x;
    // }

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
        int incre = 2;
        int answer = 0;
        // Let's suppose y is a multiple of 2 :D
        // What about 3, 5, 6, and such...
        int init = 0;
        int currproduct = 0;
        boolean negative = false;
        if (x == 0 || y == 0) {
            return 0;
        }
        //before everything, what if x or y is negative?
        if ((x < 0 || y < 0) && !(x < 0 && y < 0)) {
            negative = true;
            if (x < 0) {
                x = -x;
            }
            if (y < 0) {
                y = -y;
            }
        }
        while (y > 0) {
            while ((x << init) < y) {
                init = add(init, 1);
            }

            currproduct = x << init;
            answer = add(currproduct, answer);

            

            y = add(y, -(1 << init));

            init = 0;
        }
        if (negative) {
            return -answer;
        }
        return answer;
    }
    //     while (1 != y) {
    //         if ((y & 1) == 1) {
    //             y = add(y, -1);
    //             answer = add(x, answer);
    //         }
    //         else {
    //             answer = add(answer, (x << 1));
    //             y /= incre;
    //         }
    //     }
            
        
    //     return answer;
    // }
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
        // YOUR CODE HERE
        history.equation = equation;
        history.result = result;
        history = new EquationList("None", 0, history);

        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList currEquation = history.next;
        while (currEquation!= null) {
            System.out.println(currEquation.equation + " = " + currEquation.result);
            currEquation = currEquation.next;
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
        // YOUR CODE HERE
        EquationList currEquation = history.next;
        while (n != 1) {
            n -= 1;
            currEquation = currEquation.next;

        }
        if (currEquation != null) {
            System.out.println(currEquation.equation + " = " + currEquation.result);
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
        history = new EquationList("None", 0, null);
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
        EquationList currEquation = history.next;
        while (currEquation != null) {
            sum = sum + currEquation.result;
            currEquation = currEquation.next;
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
        int pro = 1;
        EquationList currEquation = history.next;
        while (currEquation != null) {
            pro = pro * currEquation.result;
            currEquation = currEquation.next;
        }
        return pro;
    }
}