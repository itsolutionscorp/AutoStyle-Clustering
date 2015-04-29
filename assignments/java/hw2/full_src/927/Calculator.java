import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList go = null;
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
        if (x==0) {
            return y;
        }
        if (y==0) {
            return x;
        }
        else {
            int common = x & y;
            x = x ^ y;
            y = common << 1;
        }

        int sum = x + y;


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
        // YOUR CODE HERE

        int product = 0;
        if (x==0) {
            product = 0;
        }
        if (y==0) {
            product = 0;
        } 
        while (y != 0) {
            if ((y & 1) == 1) {
                product = add(product, x);
            }
        x = x << 1;
        y = y >>> 1;
        }
        return product;
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
        // YOUR CODE HERE
        EquationList Equate = new EquationList(equation, result, go);
        go = Equate;
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
        EquationList Current = go;
        if (go != null) {
            while (Current != null) {
                Current = Current.next;
                System.out.println(go.equation + " = " + go.result);
            }
        } 
        else {
            return;
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
        int count = 0;
        EquationList Current = go;
        while (count < n && go != null) {
            count = count + 1;
            Current = Current.next;
        }
        System.out.println(go.equation + " = " + go.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        go = go.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        go = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE

        if (go == null) {
            return 0;
        }
        else { 
            EquationList Equate = go.next;
            int temp = go.result;
            while (Equate != null) {
                temp += Equate.result;
                go = go.next;
            }
        
        return temp;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        
        if (go == null) {
            return 0;
        }
        else { 
            EquationList Equate = go.next;
            int temp = go.result;
            while (Equate != null) {
                temp *= Equate.result;
                go = go.next;
            }
        
        return temp;
            }
    }
}