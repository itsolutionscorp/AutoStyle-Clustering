import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList mem = new EquationList("Start of Program", 0, null);
    EquationList mem_ptr = mem;
    int num_entries = 0;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if ((x & y) == 0) {
            return x | y;
        } else {
            int and_register = ((x & y) << 1);
            int xor_template = (x ^ y);      
            return add(xor_template,and_register);
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
        int iter = 0;
        int signcheck = 0;
        int mult_val = 0;
        if ((x==0)|(y==0)){
            mult_val = 0;
        } else {
            if (x<0) {
                x = add(~x,1);
                signcheck = (signcheck ^ 1);
            }
            if (y<0) {
                y = add(~y,1);
                signcheck = (signcheck ^ 1);
            }
            while (y!=0) {
                if ((x & 1) == 1) {
                    if ((y & 1) == 1) {
                        mult_val = add(mult_val,x << iter);
                    }
                    y = y >>> 1;
                    iter = iter + 1;

                } else {
                    x = x >>> 1;
                    iter = iter + 1;
                }
            }
        }
        if (signcheck == 0) {
            return mult_val;
        } else {
            return ~add(mult_val,-1);
        }
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
        mem_ptr.next = new EquationList(equation, result, null);
        mem_ptr = mem_ptr.next;
        num_entries = num_entries + 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(num_entries);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int iter = 1;
        if ((n > 0) & (n <= num_entries)) {
            EquationList temp_ptr1 = mem_ptr;
            EquationList temp_ptr2 = mem;
            System.out.println(temp_ptr1.equation + " = " + temp_ptr1.result);
            while (iter < n) {
                if (temp_ptr1 == temp_ptr2.next) {
                    System.out.println(temp_ptr2.equation + " = " + temp_ptr2.result);
                    temp_ptr1 = temp_ptr2;
                    temp_ptr2 = mem;
                    iter = iter + 1;
                } else { 
                    temp_ptr2 = temp_ptr2.next;
                }
            }
        } else if (n > num_entries) {
            printAllHistory();
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList temp_ptr2 = mem;
        while (temp_ptr2.next != mem_ptr) {
            temp_ptr2 = temp_ptr2.next;
        }
        mem_ptr = temp_ptr2;
        num_entries = num_entries - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        mem_ptr = mem;
        num_entries = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp_ptr2 = mem.next;
        int cumsum = 0;
        while (temp_ptr2 != null) {
            cumsum = add(cumsum,temp_ptr2.result);
            temp_ptr2 = temp_ptr2.next;
        }
        return cumsum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList temp_ptr2 = mem.next;
        int cumprod = temp_ptr2.result;
        temp_ptr2 = temp_ptr2.next;
        while (temp_ptr2 != null) {
            cumprod = multiply(cumprod,temp_ptr2.result);
            temp_ptr2 = temp_ptr2.next;
        }
        return cumprod;
    }
}