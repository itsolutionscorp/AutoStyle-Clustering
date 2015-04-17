import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqList;
    public int numEntries;
    
    public Calculator() {
        eqList=null;
        numEntries=0;
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
        // YOUR CODE HERE
        int andVal = x & y; 
        int xorVal = x ^ y;
        while(andVal != 0) {
            int tempVal=andVal << 1;
            andVal = xorVal & tempVal;
            xorVal = xorVal ^ tempVal; 
        }
        this.saveEquation(x + " + " + y, xorVal);
        return xorVal;
    }

    public int add(int x, int y, boolean save) {
        // YOUR CODE HERE
        int andVal = x & y; 
        int xorVal = x ^ y;
        while(andVal != 0) {
            int tempVal=andVal << 1;
            andVal = xorVal & tempVal;
            xorVal = xorVal ^ tempVal; 
        }
        if (save) {
            this.saveEquation(x + " + " + y, xorVal);
        }
        return xorVal;
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
        int summed = 0;
        int shift = 0;
        int ycopy = y;
        while (y != 0) {
            int multiply = y & 1;
            if (multiply == 1) {
                summed = add(summed, x << shift,false);
            }
            y = y >>> 1;
            shift+=1;
        }
        this.saveEquation(x + " * " + ycopy,summed);
        return summed;
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
        eqList= new EquationList(equation, result, eqList);
        numEntries+=1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(numEntries);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList parser = eqList;
        while(n > 0 && parser !=null){
            System.out.println(parser.equation + " = " + parser.result);
            parser=parser.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqList = eqList.next;
        numEntries-=1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList=null;
        numEntries=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList parser = eqList;
        while (parser!=null) {
            sum += parser.result;
            parser=parser.next;
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
        EquationList parser = eqList;
        while (parser!=null) {
            product *=parser.result;
            parser=parser.next;
        }
        return product;
    }
}
