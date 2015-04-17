import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    EquationList history;
    Boolean print_all_history = false;

    public int add(int x, int y) {
        int a = x & y;
            a = a << 1;
        int b = x ^ y;
        int c =  a & b;
        if (c == 0) {
            int d = a ^ b;
            return d;
        } else {
            return add(a, b);
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
        // var is a variable that compare with y, it is n-th order of 2.
        int var = 1;
        int n = 0;
        while (y > var) {
            var = var << 1;
            n = add(n, 1);
        }
        /* After the while loop, var is the closest number of 2 to some power *
           so we can subtract it by y, to see the difference.                 *
           Then, we multiply x by (2 to n-th power), which is bigger than the *
           actual product, thus we should subtract x from the product by a    *
           (difference) of times to get the true product. */
        int difference = add(var, - y);
        int product = x << n;
        while (difference > 0) {
            product = add(product, -x);
            difference = add(difference, -1);
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
        if (history == null) {
            history = new EquationList(equation, result, null);
        } else {
            history = new EquationList(equation, result, history);
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
        // YOUR CODE HERE
        if (history == null) {
            return;
        }
        print_all_history = true;
        EquationList pointer = history;
        int history_length = 0;
        while (pointer != null) {
            history_length += 1;
            pointer = pointer.next;
        }
        printHistory(history_length);
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
        EquationList pointer = history;
        while (n > 1) {
            if (print_all_history) {
                System.out.print(pointer.equation);
                System.out.print(" = ");
                System.out.println(pointer.result);
            }
            pointer = pointer.next;
            n -= 1;
        }
        System.out.print(pointer.equation);
        System.out.print(" = ");
        System.out.println(pointer.result);
        print_all_history = false;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history == null) {
            return;
        }
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
        // YOUR CODE HERE
        int sum = 0;
        if (history == null) {
            return 0;
        } else {
            EquationList pointer = history;
            while (pointer != null) {
                sum += pointer.result;
                pointer = pointer.next;
            }
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
        if (history == null) {
            return 1;
        } else {
            EquationList pointer = history;
            while (pointer != null) {
                product *= pointer.result;
                pointer = pointer.next;
            }
        }
        return product;
    }
}