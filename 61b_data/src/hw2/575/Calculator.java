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
        Calculator machine = new Calculator();
        // YOUR CODE HERE
        int count = 0;
        int carry = 0;
        int answer = 0;

        if (y == 0) {
            return x;
        }
        else if (x == 0) {
            return y;
        }

        while (count <= 31) {
            int xBit = machine.getBit(x, count);
            int yBit = machine.getBit(y, count);
            if ((xBit == 1) && (yBit == 1)) {
                carry = machine.setBitToOne(carry, count + 1);
            }
            else if ((xBit == 1) || (yBit == 1)) {
                answer = machine.setBitToOne(answer, count);
            }
            count += 1;        
        }
        return machine.add(answer, carry);
    }

    public int getBit(int x, int i) {
        x = x >> i;
        if ( (x & 1) == 1) {
            return 1;
        }
        else {
            return 0;
        }
    } 

    public int setBitToOne(int x, int i) {
        if (i <= 31) {
            return x | (1 << i);
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
        // YOUR CODE HERE
        Calculator machine = new Calculator();

        if (x == 0 || y == 0) {
            return 0;
        }
        int count = 0;
        int answer = 0;

        while (count <= 31) {
            int yBit = machine.getBit(y, count);
            if (yBit == 1) {
                answer = machine.add(answer, (x << count));
            }
            count += 1;
        }
        return answer;
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
            this.history = new EquationList(equation, result, null);
        }
        else {
            this.history = new EquationList(equation, result, this.history);
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
        EquationList p = history;
        if (p == null) {
            return;
        }

        while (p.next != null) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
        }
        System.out.println(p.equation + " = " + p.result);
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
        int count = 1;
        EquationList p = history;

        while (p != null && count <= n) {
            System.out.println(p.equation + " = " + p.result);
            //System.out.println(1);
            p = p.next;
            //System.out.println(2);
            count += 1;
        }
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
        else {
            history = history.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
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
        EquationList p = history;
        int sum = 0;

        if (history == null) {
            return sum;
        }
        else {
            while (p.next != null) {
                sum += p.result;
                p = p.next;
            }
            sum += p.result;
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
        // YOUR CODE HERE
        EquationList p = history;
        int product = 1;

        if (history == null) {
            return product;
        }
        else {
            while (p.next != null) {
                product *= p.result;
                p = p.next;
            }
            product *= p.result;
            return product;
        }
    }
}