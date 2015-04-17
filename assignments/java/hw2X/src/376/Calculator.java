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
        // Carry bit
        int carry = 0;

        // Index counter for 32 bit int
        int index = 0;

        // 32 bit integer result
        int result = 0;
        
        // Loop through each bit from right to left
        while (index < 32) {

            // Mask to get the bit at index
            int mask = 1 << index;

            // Bit of x at index
            int x_bit = (x & mask) >>> index;

            // Bit of y at index
            int y_bit = (y & mask) >>> index;

            // Sum of x bit and y bit and carry bit
            int sum = x_bit + y_bit + carry;

            switch (sum) {
                case 3: 
                    // 1 + 1 with carry bit
                    result = result | mask;
                    carry = 1;
                    break;
                case 2: 
                    // 1 + 1 with not carry bit
                    carry = 1;
                    break;
                case 1: 
                    // 1 + 0 or 0 + 1
                    result = result | mask;
                    carry = 0;
                    break;
                default:
                    // 0 + 0
                    carry = 0;
                    break;
            }

            index ++;
        }

        return result;
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
        // Keep the multipllicants positive by keep track of the sign of result
        boolean negative = false;

        // Get the absolute values of x and y
        if (x >= 0 && y < 0) {
            y = -y;
            negative = true;
        } else if (x < 0 && y >= 0) {
            x = -x;
            negative = true;
        } else if (x < 0 && y < 0) {
            x = -x;
            y = -y;
        }

        int result = 0;
        while (y != 0) {
            // When odd, use add
            if ((y & 1) == 1) {
                result = add(result, x);
            }

            // When even, x = x / 2 and y = y * 2
            x <<= 1;
            y >>= 1;
        }

        // If the result is supposed to be negative, negate the result
        return negative? -result : result;
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
        if (history == null) {
            history = new EquationList(equation, result, null);
        } 

        // If the current history is not empty
        // Creates a pointer to traverse to the end of EquationList
        else {
            EquationList historyPtr = history;

            while (historyPtr.next != null) {
                historyPtr = historyPtr.next;
            }

            // Add new one at the end
            historyPtr.next = new EquationList(equation, result, null);
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
        // Create a pointer
        EquationList historyPtr = history;

        // Use the pointer to check the length of the EquationList
        int length = 0;

        while (historyPtr != null) {
            length += 1;
            historyPtr = historyPtr.next;
        }

        // Traverse through the length
        printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // Create a pointer
        EquationList historyPtr = history;

        // Use the pointer to check the length of the EquationList
        int length = 0;

        while (historyPtr != null) {
            length += 1;
            historyPtr = historyPtr.next;
        }

        int printCounter = 0;

        while (n > 0) {
            // Reset pointer
            historyPtr = history;
            
            // Traverse the data structure in reverse order
            int index = 1;

            while (index < length - printCounter) {
                historyPtr = historyPtr.next;
                index += 1;
            }

            // prints out the equation
            System.out.println(historyPtr.equation + " = " + historyPtr.result);

            n -= 1;
            printCounter += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // Break out if history is already null
        if (history == null) {
            return;
        } 

        // History is null if its next is already null
        else if (history.next == null) {
            history = null;
        } 

        // Find the case where history's next next is null
        else {
            EquationList historyPtr = history;

            while (historyPtr.next.next != null) {
                historyPtr = historyPtr.next;
            }

            historyPtr.next = null;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // Repeatedly undo until history is empty
        while (history != null) {
            undoEquation();
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int result = 0;

        EquationList historyPtr = history;

        while (historyPtr != null) {
            result = add(result, historyPtr.result);
            historyPtr = historyPtr.next;
        }

        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int result = 1;

        EquationList historyPtr = history;

        while (historyPtr != null) {
            result = multiply(result, historyPtr.result);
            historyPtr = historyPtr.next;
        }

        return result;
    }
}