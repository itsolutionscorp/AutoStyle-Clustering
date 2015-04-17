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
    public int add(int x, int y) {
        // YOUR CODE HERE]
        
        /*              Testing
        System.out.println("X: " + x + " BinX: " + Integer.toBinaryString(x));
        System.out.println("Y: " + y + " BinY: " + Integer.toBinaryString(y));
        System.out.println("");
        */ 
        
        char binaryXBit;
        char binaryYBit;
        boolean carryOver = false;

        StringBuilder starter = new StringBuilder();

        int count = 0;
        while ((count < 32)) {      //CHANGE BACK TO 32
            try {
                binaryXBit = Calculator.getBit(x, count);
            } catch(Exception e) {
                binaryXBit = '0';
            }

            try {
                binaryYBit = Calculator.getBit(y, count);
            } catch(Exception e) {
                binaryYBit = '0';
            }

            /*          Testing 
            System.out.println("Starter: " + starter);
            System.out.println("Carry Over? " + carryOver);
            System.out.println("BinX: " + binaryXBit);
            System.out.println("BinY: " + binaryYBit);
            System.out.println("");
            */


            //=====================================================================================
            if (binaryXBit != binaryYBit) {                 // They're not equal (so (0, 1) or (1,0))
                if (carryOver) { 
                    starter.insert(0, "0");                 // 1 + 0 + 1 (leaves carryover as true)

                } else {
                    starter.insert(0, "1");                 // 1 + 0 (leaves carryover as false)
                }
            //=====================================================================================
            } else if (binaryXBit == '1') {                   // They're both 1
                if (carryOver) {
                    starter.insert(0, "1");                 // 1 + 1 + 1
                } else {
                    carryOver = true;
                    starter.insert(0, "0");                 // 1 + 1 (changes carryover to true)
                }
            //=====================================================================================
            } else {                                        // They're both 0 
                if (carryOver) {
                    starter.insert(0, "1");                 // 0 + 0 + 1
                    carryOver = false;                      // Changes carryover because the sum doesn't carry over
                } else {
                    starter.insert(0, "0");                 // 0 + 0 
                }
            }
            //=====================================================================================
            count++;
        }

        //          Testing
        //System.out.println("BinTotal: " + starter);
        //System.out.println("");

        int base = 2;
        long backToLong = Long.parseLong(starter.toString(), base);
        
        int backToInteger = (int) backToLong;
        return backToInteger;
    }

    private static char getBit(int x, int i) {
        StringBuilder binaryX = new StringBuilder();
        binaryX.append(Integer.toBinaryString(x));
        

        StringBuilder reversedBinaryX = new StringBuilder(); 
        reversedBinaryX.append(binaryX); // Reverses the string so I can use i in charAt()
        reversedBinaryX.reverse().toString();
        

        return reversedBinaryX.charAt(i);
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
        int solution;
        boolean resultWillBeNegative = ((x < 0) && (y > 0)) || ((x > 0) && (y < 0));
        x = Math.abs(x); 
        y = Math.abs(y);        //Checks if the result will be negative and then converts x and y to their positive counterparts
                                    //I'll use the boolean later in the method to determine if the result should be negative or not 
        
        if (y == 0) {
            solution = 0;
        
        } else if (y == 1) {
            solution = x;
        
        } else if (isEven(y)) {
            x = x << 1;                         // x = x*2
            y = y >> 1;                         // y = y/2
            solution = multiply(x, y);          // multiply(2x, .5y)
        
        } else {
            solution = add(multiply(x, add(y, -1)), x);     // Example: 10*5 == (10*4) + 10  
        }

        if (resultWillBeNegative) {
            return add((~ solution), 1);        //Inverse of positive solution + 1 is its negative counterpart
        }
        return solution;    
    }

    private boolean isEven (int x) {
        //returns whether x is an even number or not 
        return (getBit(x, 0) == '0');
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
    EquationList calcHistory = null;                            //Member variable is initalized as null

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        EquationList newEntry = new EquationList(equation, result, null);

        if (calcHistory == null) {                              //If it hasn't been touched, initialize it as the new entry
            calcHistory = newEntry;
        
        } else {
            //Otherwise add it to the front
            newEntry.next = calcHistory;
            calcHistory = newEntry;
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
        int historyLength = calcLength(calcHistory);
        printHistory(historyLength);
    }

    private int calcLength(EquationList history) {
        if (history == null) {
            return 0;
        } else {
            return 1 + calcLength(history.next);
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
        EquationList pointer = calcHistory;

        while (n > 0) {
            
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
            n--;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        calcHistory = calcHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        calcHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        return recursiveHelperAdd(calcHistory);
    }

    private int recursiveHelperAdd(EquationList history) {
        if (history == null)
            return 0;

        return history.result + recursiveHelperAdd(history.next);
    }


    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        return recursiveHelperMul(calcHistory);
    }

    private int recursiveHelperMul(EquationList history) {
        if (history == null)
            return 1;

        return history.result * recursiveHelperMul(history.next);
    }
}
