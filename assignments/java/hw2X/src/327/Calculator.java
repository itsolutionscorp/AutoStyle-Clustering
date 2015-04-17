import list.EquationList;

public class Calculator {
    public EquationList latest; // Holds latest EquationList
    public int steps;           // Holds number of equations in history

    public Calculator() {
        this.latest = null;
        this.steps = 0;
    }

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carryOverPlace = x & y; // Only 1 & 1 = 1, signifies carry-over

        // If no carry-over operations are needed
        if (carryOverPlace == 0) {
            return x ^ y;   // 0 + 0 = 0, 0 + 1 = 1, 1 + 0 = 1
        }

        // Otherwise, instantiation of extra variables
        int a = x;          // Copy of x that is modified to contain carry-overs
        int b = y;          // Copy of y that is modified to remove carry-overs
        int c;              // Part of x up to next carry-over
        int d;              // Part of y up to next carry-over
        int count = findCount(carryOverPlace); // Next carry-over
        int carried = 1;    // Carried over 1, i.e. 100 for xx ^ xx

        while (carryOverPlace != 0) {
            // Splice out lower figure places/columns up to and including first carry-over
            count = findCount(carryOverPlace);
            c = fetchUpToPlace(a, count, 1);
            d = fetchUpToPlace(b, count, 1);

            // Combining sum results into top addends
            c = c ^ d; 
            a = truncatePlace(a, count, 0);
            a = a ^ c;

            // Form the carry-over 1 in next digit
            for (int i = count; i > 0; i--) { 
                carried = carried << 1;
            }
            // Apply carry-over recursively in case triggers another carry-over
            a = add(a, carried); 
            carried = 1;
            
            // Simplify lower addend into zeroes
            b = truncatePlace(b, count, 0); 
            carryOverPlace = a & b;
        }

        return a ^ b;
    }

    protected int findCount(int carries) {
        // Helper returns place of first carry-over (first 1)
        int first = 0;
        int singlePlace = 0;
        while (singlePlace == 0) {
            singlePlace = fetchUpToPlace(carries >> first, 1, 0);
            first++;
        }
        return first;
    }

    protected int truncatePlace(int num, int place, int start) {
        // Helper turns significant values lesser and equal than place to 0
        int shift = num;
        for (int i = start; i < place; i++) {
            shift = shift >> 1;
        }
        for (int i = start; i < place; i++) {
            shift = shift << 1;
        }
        return shift;
    }

    protected int fetchUpToPlace(int num, int place, int start) {
        // Helper turns significant values larger than place to 0
        return num ^ truncatePlace(num, place, start);
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
        if (x == 0 || y == 0) {
            return 0;
        }
        if (y < 0) { // Make y positive
            x = flipSign(x);
            y = flipSign(y);
        }
        if (x == 1) {
            return y;
        }
        else if (y == 1) {
            return x;
        }
        else if (isPowerOfTwo(y)) {
            int result = shiftByTwos(x, y);
            return result;
        }
        else { 
            int power = findPowerOfTwo(y);
            int result = shiftByTwos(x, power);
            for (int count = power; count < y; count++) {
                result = add(result, x);
            }
            return result;
        }
    }

    protected int flipSign(int num) {
        // Flips sign of number
        if (num == 0) {
            return num;
        }
        else {
            return add(~num, 1);
        }
    }

    protected boolean isPowerOfTwo(int num) {
        // Helper checks if num is a power of 2
        int firstOne = findCount(num);
        int onlyOne = 1 << firstOne; // Creates power of 2 at first place with a 1
        onlyOne = onlyOne >> 1;
        if (onlyOne == num) {
            return true;
        }
        return false;
    }

    protected int shiftByTwos(int num, int power) {
        // Helper uses << to multiply by powers of 2
        while (power > 1) {
            num = num << 1;
            power = power >> 1;
        }
        return num;
    }

    protected int findPowerOfTwo(int num) {
        // Helper finds highest power of 2 less than num
        int power = 1;
        while (power < num) {
            power = power << 1;
        }
        return power >> 1;
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
        this.latest = new EquationList(equation, result, this.latest);
        this.steps++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(this.steps);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList expr = this.latest;
        int count = 0;
        while (expr != null && count < n) {
            System.out.println(expr.equation + " = " + expr.result);
            expr = expr.next;
            count++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        this.latest = this.latest.next;
        this.steps--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.latest = null;
        this.steps = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int[] results = new int[this.steps];
        EquationList curr = this.latest;
        for (int i = 0; i < this.steps; i++) {
            results[i] = curr.result;
            curr = curr.next;
        }
        int sum = 0;
        for (int addend : results) {
            sum = sum + addend;
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
        int[] results = new int[this.steps];
        EquationList curr = this.latest;
        for (int i = 0; i < this.steps; i++) {
            results[i] = curr.result;
            curr = curr.next;
        }
        int prod = 1;
        for (int multend : results) {
            prod = prod * multend;
        }
        return prod;
    }
}