import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    

    public EquationList constantEqList = new EquationList("", 0, null);


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
        
        // if (x < 0) {
        //     int z = y;
        //     x = y;
        //     y = z;
        // }

        int a;
        int b;
        int c;
        while (y != 0) {
            a = x ^ y;
            b = (x & y) << 1;
            x = a;
            y = b;

            // int c = a ^ b;
            

            // int a = x ^ y;
            // y = ()


        }
        // int a = x ^ y;
        // int b = (x & y) << 1;
        
        // if (x < 0) {
        //     int c = ~ x;
        //     int d = c ^ 1;
        //     int e = d ^ b;
        //     return e;
        // }

        // int c = a ^ b;

        // if (x < 0) {
        //     int d = (~ c) & 1;
        //     return d;
        // }
        // System.out.println(a);
        // System.out.println(b);
        // System.out.println(c);
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
        
        int a = 0;
        int b = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                a = x;
                y = y >>> 1; 
            } else {
                a = 0;
                y = y >>> 1;
            }
            b = add(b, a);
            x = add(x, x);
        }
        return b;



        // int c = 0;
        // while (y != 0) {
        //     c = add(c, x);

        //     if (y < 0) {
        //         c = add(c, x);
        //         y = add(y, 1);
        //     } else {
        //         c = add(c, x);
        //         y = add(y, ~ 1);
        //     }


        // }

        // return c; 
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
        constantEqList = new EquationList(equation, result, constantEqList);
        


        // YOUR CODE HERE

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        
        // EquationList temp = constantEqList;
        // while (temp.next != null) {
        //     System.out.println(temp.equation + " = " + temp.result);
        //     temp = temp.next;
        // }
        


        int size = 0;
        EquationList temp = constantEqList;
        while (temp.next != null) {
            size ++;
            temp = temp.next;
        }
        printHistory(size);

        // while (temp.next != null) {
        //     printHistory(x);
        //     x++;
        // }

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
        int counter = 0;
        EquationList temp = constantEqList;
        while (counter < n) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            counter++;

            // if (counter == n) {
            //     System.out.println(temp.equation + " = " + temp.result);
            // }
            
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        constantEqList = constantEqList.next;

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        constantEqList = new EquationList("", 0, null);
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
        EquationList temp = constantEqList;
        while(temp.next != null) {
            sum = sum + temp.result;
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
        // YOUR CODE HERE
        int product = 1;
        EquationList temp = constantEqList;
        while(temp.next != null) {
            product = product * temp.result;
            temp = temp.next;

        }
        return product;
    }
}