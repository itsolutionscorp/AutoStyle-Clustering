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
    private EquationList initial;

    public int add(int x, int y) {
        int result = 0;
        int carry = 0;

        for (int i = 0; i < 32; i++) {
            if ((getBit(x, i) == 0) && (getBit(y, i) == 0) && (getBit(carry, i) == 0)) {
                result = setBit(result, i, 0);
            }

            else if ((getBit(x, i) == 0) && (getBit(y, i) == 0) && (getBit(carry, i) == 1)) {
                result = setBit(result, i, 1);
            }

            else if ((getBit(x, i) == 1) && (getBit(y, i) == 0) && (getBit(carry, i) == 0)) {
                result = setBit(result, i, 1);
            }

            else if ((getBit(x, i) == 0) && (getBit(y, i) == 1) && (getBit(carry, i) == 0)) {
                result = setBit(result, i, 1);
            }

            else if ((getBit(x, i) == 1) && (getBit(y, i) == 0) && (getBit(carry, i) == 1)) {
                carry = 2 << i;
                result = setBit(result, i, 0);
            }

            else if ((getBit(x, i) == 0) && (getBit(y, i) == 1) && (getBit(carry, i) == 1)) {
                carry = 2 << i;
                result = setBit(result, i, 0);
            }            
                
            else if ((getBit(x, i) == 1) && (getBit(y, i) == 1) && (getBit(carry, i) == 0)) {
                carry = 2 << i;
                result = setBit(result, i, 0);
            }

            else if ((getBit(x, i) == 1) && (getBit(y, i) == 1) && (getBit(carry, i) == 1)) {
                carry = 2 << i;
                result = setBit(result, i, 1);
            }            
        }
        return result;
    }

    public int getBit(int variable, int location) {
        variable = variable >>> location;
        variable = variable << 31;
        variable = variable >>> 31;
        return variable;
    }

    public int setBit(int variable, int location, int set) {
        int result = 0;
        int hold = 1;
        int value = getBit(variable, location);
        if (value == set) {
            return variable;
        }

        else if (set == 0) {
            hold = hold << location;
            result = hold ^ variable;
            return result;
        }

        else if (set == 1) {
            set = set << location;
            result = set | variable;
            return result;           
        }

        else {
            // invalid set
            return -1;
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
        int result1 = 0;
        int result2 = 0;
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    if ((getBit(x, j) == 0) && (getBit(y, i) == 0)) {
                        result2 = setBit(result2, j, 0);
                    }

                    else if ((getBit(x, j) == 1) && (getBit(y, i) == 0)) {
                        result2 = setBit(result2, j, 0);
                    }

                    else if ((getBit(x, j) == 0) && (getBit(y, i) == 1)) {
                        result2 = setBit(result2, j, 0);
                    }

                    else if ((getBit(x, j) == 1) && (getBit(y, i) == 1)) {
                        result2 = setBit(result2, j, 1);
                    }
                }
                result1 = add(result1, result2 << i);
                result2 = 0;
            }
            return result1;
        }
    //}

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
 /*       while (saved != null) {
            saved.next = new EquationList(equation, result, null);
        }*/
        EquationList saved = new EquationList(equation, result, initial);
            initial = saved;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = initial;
        while (temp != null) {
                System.out.printf("%s = %d%n", temp.equation, temp.result);
                temp = temp.next;
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
        // It will print out n equations when you call printHistory() if there are n equations available.
        EquationList temp = initial;
        for (int i = 0; i < n; i++) {
            if (temp == null) {
                return;
            }
            else {
                System.out.printf("%s = %d%n", temp.equation, temp.result);
                temp = temp.next;
            }

        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (initial == null) {
            System.out.printf("");
        }
        else {
        initial = initial.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (initial != null) {
            initial = initial.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = initial;
        int sum = 0;
        while (temp != null) {
            sum += temp.result;
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
        EquationList temp = initial;
        int product = 1;
        while (temp != null) {
            product *= temp.result;
            temp = temp.next;
        }
        return product;
    }
}