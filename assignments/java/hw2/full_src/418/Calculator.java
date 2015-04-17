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
    public static int add(int x, int y) {
        // YOUR CODE HERE
        int answer = 0;
        int carry = 0;
        int counter = 0;
        int restX = x;
        int restY = y;

        int bitX = getBit(x, counter);
        int bitY = getBit(y, counter);
        int bitC = 0;

        while (((restX | restY | bitC) != 0) && (counter < 32)) {
            // only exit this addition loop if all remaining numbers are zero, OR if the number is so big that we are exceeding the maximum size of integer

            bitX = getBit(x, counter);
            bitY = getBit(y, counter);

            if ((bitX & bitY & bitC) == 1) {
                // 1 + 1 + 1 --> result of 1, carry a 1
                answer = setBit(answer, counter, 1);
                carry = setBit(carry, counter + 1, 1);
            }


            else if (((bitX & bitY) | (bitX & bitC) | (bitY & bitC)) == 1) {
                // 1 + 1 + 0 --> result of 0, carry a 1
                answer = setBit(answer, counter, 0);
                carry = setBit(carry, counter + 1, 1);
            }
            
            else if ((bitX ^ bitY ^ bitC) == 1) { 
                // 1 + 0 + 0 --> result of 1
                answer = setBit(answer, counter, 1);
                carry = setBit(carry, counter + 1, 0);
            }

            else if ((bitX | bitY | bitC) == 0) { 
                // 0 + 0 + 0 --> result of 0
                answer = setBit(answer, counter, 0);
                carry = setBit(carry, counter + 1, 0);
            }

            // eliminates the most recently added bits
            restX = restX >>> 1;
            restY = restY >>> 1;
            counter = counter + 1;
            bitC = getBit(carry, counter);
        }
        return answer;
    }

    public static int getBit(int number, int i) {
        int newNumber = number >>> i;
        int bit = newNumber & 1;
        return bit;
    }

    public static int setBit(int number, int i, int z) {
        int setNumber = 0;
        if (z == 1) {
            int positionOfOne = 1 << i;
            setNumber = number | positionOfOne;
        }
        else { // z == 0
            int positionOfZero = 0 << i;
            setNumber = number ^ positionOfZero;
        }
        return setNumber;
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
        int productSum = 0;
        int restX = x;
        int restY = y;

        while (restX != 0) {
            if ((1 & restX) != 0) {
                productSum = add(productSum, restY);
            }
            restX = restX >>> 1;
            restY = restY << 1;
        }
        return productSum;
    }
        /*
        if (y == 1) {
            return x;
        }
        else if ()
        else {
            y = y >>> 1;
            return multiply(add(x, x), y);
        } */

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/

    EquationList history;

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
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
        // YOUR CODE HERE
        EquationList pointer;
        if (history != null) {
            pointer = history;
            while (pointer != null) {
                System.out.println(pointer.equation + " = " + pointer.result);
                pointer = pointer.next;
            }
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
        EquationList pointer;
        int counter = n;
        if (history != null) {
            pointer = history;
            while ((counter > 0) && (pointer != null)) {
                System.out.println(pointer.equation + " = " + pointer.result);
                pointer = pointer.next;
                counter = counter - 1;
            }
        }
        
    }   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList pointer;
        if (history != null) {
            pointer = history.next;
            history = pointer;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        EquationList pointer;
        if (history != null) {
            history = null;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList pointer = history;
        int sum = 0;
        while (pointer != null) {
            sum = sum + pointer.result;
            pointer = pointer.next;
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
        EquationList pointer = history;
        int product = 1;
        while (pointer != null) {
            product = product * pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}