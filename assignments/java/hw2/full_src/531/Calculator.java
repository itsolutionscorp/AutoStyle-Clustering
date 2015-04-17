import list.EquationList;

public class Calculator {
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
        while (y != 0) {
            int a = x^y; //add
            int b = x&y; //find carriers
            b = b<<1;    //move carriers
            x = a;
            y = b;
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
        // find product
        int count = 0;
        int product = 0;
        while (count != Math.abs(x)) {
            product = add(product,y);
            count = count +1;
        }
        //Test sign with first leftmost bit
        int test = x >>> 31;
        int i = test&1;
        if (i == 1) {
            return add((~product),1);
        } else {
            return product;
        }
    }




    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * denexts.
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
        EquationList ptr = history;
        while (ptr != null) {
            System.out.println(ptr.equation +" = "+ ptr.result);
            ptr = ptr.next;
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
        EquationList ptr = history;
        int count = 1;
        while (count <= n) {
            System.out.println(ptr.equation + " = " + ptr.result);
            ptr = ptr.next;
            count = count + 1;
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
        EquationList ptr = history;
        int sum = 0;
        if (ptr == null){
            return sum;
        } else {
            while (ptr != null) {
                sum = add(sum,ptr.result);
                ptr = ptr.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList ptr = history;
        int product = 1;
        if (ptr == null) {
            return product;
        } else {
            while (ptr != null) {
                product = multiply(product,ptr.result);
                ptr = ptr.next;
            }
            return product;
        }
    }
}