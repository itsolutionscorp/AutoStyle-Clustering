import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList list = new EquationList("",0,null);
    public int num_history = 1;
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
        int carry = (x & y) << 1;
        int result = x ^ y;
        int v1_xor = result;
        int v2_carry = carry;
        while (carry != 0) {
            carry = (v1_xor & v2_carry) << 1;
            result = v1_xor ^ v2_carry;
            v1_xor = result;
            v2_carry = carry;
        }
        result = v1_xor ^ v2_carry;
        return result;
    }

    public int getbit(int x, int n) {
        String binary_string = Integer.toBinaryString(x);
        if (n >= binary_string.length()) {
            return 0;
        }
        char digit_char = binary_string.charAt(n);
        int digit = Character.getNumericValue(digit_char);
        return digit;
    }
    
    /*public int setbit(int x, int n) {
        String binary_string = Integer.toBinaryString(x);
        //System.out.println(binary_string);
        // reverse the binary string so that the first digit is starting on the left
        binary_string = new StringBuffer(binary_string).reverse().toString();
        if (n < binary_string.length()) {
            binary_string = binary_string.substring(0,n) + '1' + binary_string.substring(n+1);
        }
        else {
            while (binary_string.length() != n) {
                binary_string = binary_string + '0';
            }
            binary_string = binary_string + '1';
        }
        //System.out.println(binary_string);
        //reverse the binary string back
        binary_string = new StringBuffer(binary_string).reverse().toString();
        //System.out.println(binary_string);
        int result = Integer.parseInt(binary_string, 2);
        //System.out.println(result);
        return result;
    } */
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
        int sum = 0;
        int y_bit;
        while (y != 0) {
            //y_bit = getbit(y,0);
            y_bit = y & 1;
            if (y_bit == 1) {
                sum = add(sum,x);
            }
            x = x << 1;
            y = y >>> 1;
        }
        return sum;
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
        EquationList save = list;
        while (save.next != null){
            save = save.next;
        }
        save.next = new EquationList(equation,result,null);
        num_history = num_history + 1;
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
        printHistory(num_history-1);
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
        EquationList save = list;
        int i = num_history - n;
        while (i != 0) {
            save = save.next;
            i = i - 1;
        }
        i = n;
        int j;
        EquationList n_history;
        while (i != 0) {
            n_history = save;
            j = i;
            while (j != 1){
                n_history = n_history.next;
                j = j - 1;
            }
            System.out.println(n_history.equation + " = " + n_history.result);
            i = i -1;
        }
    }    

    /**
     * 
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList save = list;
        while (save.next.next != null) {
            save = save.next;
        }
        save.next = null;
        num_history = num_history - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        list = new EquationList("",0,null);
        num_history = 1;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (num_history == 1) {
            return 0;
        }
        EquationList save = list.next;
        int sum = 0;
        while (save != null) {
            sum = sum + save.result;
            save = save.next;
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
        if (num_history == 1) {
            return 1;
        }
        EquationList save = list.next;
        int product = 1;
        while (save != null) {
            product = product * save.result;
            save = save.next;
        }
        return product;
    }
}