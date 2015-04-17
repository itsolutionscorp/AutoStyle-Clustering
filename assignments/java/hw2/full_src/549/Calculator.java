import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList currentSavedEquation = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int xor = x ^ y; 
        int and = x & y;
        if (and != 0){
            return add(xor, and << 1);
        } else{
            return xor;
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
        int biggerInt = 0, smallerInt = 0;
        boolean odd = false;
        if (x == 0 || y == 0)
            return 0;
        if (x == 1){
            return y;
        } else if(y==1){
            return x;
        } else if (x >0 && y > 0){
            if (x > y){
                biggerInt = x;
                smallerInt = y;
            } else{
                biggerInt = y;
                smallerInt = x;
            }
            return multiplyHelper(biggerInt, smallerInt, biggerInt, odd);
        }else if (x < 0 && y < 0){
            x = ~ add(x, -1);
            y = ~ add(y, -1);
            if (x > y){
                biggerInt = x;
                smallerInt = y;
            } else{
                biggerInt = y;
                smallerInt = x;
            }
            return multiplyHelper(biggerInt, smallerInt, biggerInt, odd);
        } else if (x < 0){
            x = ~ add(x, -1);
            if (x > y){
                biggerInt = x;
                smallerInt = y;
            } else{
                biggerInt = y;
                smallerInt = x;
            }
            return add(~multiplyHelper(biggerInt, smallerInt, biggerInt, odd), 1);
        } else{
            y = ~ add(y, -1);
            if (x > y){
                biggerInt = x;
                smallerInt = y;
            } else{
                biggerInt = y;
                smallerInt = x;
            }
            return add(~multiplyHelper(biggerInt, smallerInt, biggerInt, odd), 1);     
        }
    }

    public int multiplyHelper(int x, int y, int xOriginal, boolean odd){
        if ((y == 1) && odd){
            return add(x, xOriginal);
        } else if ((y == 1) && (odd == false)){
            return x;
        }else if ((y & 1) == 1) /* check if y is odd */{
            int updateXOriginal = x;
            int updateY = add(y, -1);
            odd = true;
            return multiplyHelper(x, updateY, updateXOriginal, odd);
        } else {
            int updateX = x << 1;
            int updateY = y >>> 1;
            return multiplyHelper(updateX, updateY, xOriginal, odd);
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
        EquationList nextEquation = new EquationList(equation, result, currentSavedEquation);
        currentSavedEquation = nextEquation;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = currentSavedEquation;
        int count = 0;
        while (temp != null){
            count += 1;
            temp = temp.next;
        }
        printHistory(count);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp = currentSavedEquation;
        while (n != 0){
            if (temp == null)
                return;
            String equation = temp.equation;
            int result = temp.result;
            System.out.println(equation + " = " + result);
            temp = temp.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (currentSavedEquation != null)
            currentSavedEquation = currentSavedEquation.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        if (currentSavedEquation != null){
            while (currentSavedEquation != null){
                currentSavedEquation = currentSavedEquation.next;
            }
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temp = currentSavedEquation;
        if (temp == null)
            return sum;
        while (temp != null){
            sum = add(sum, temp.result);
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
        int product = 1;
        EquationList temp = currentSavedEquation;
        if (temp == null)
            return product;
        while (temp != null){
            product = multiply(product, temp.result);
            temp = temp.next;
        }
        return product;
    }
}