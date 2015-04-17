import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList stringOfEqs = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) 
    {
        // YOUR CODE HERE
        /* Was going to originally compare it bit by bit in a string, but 
        I guess that wasn't the purpose of this homework assignment
        String first = Integer.toBinaryString(x);
        String second = Integer.toBinaryString(y);
        int argumentOne = Integer.parseInt(first);
        int argumentTwo = Integer.parseInt(second);
        blah blah blah while loop code etc
        answer = Integer.parseInt(finalBinaryString, 2);
        return answer;*/
        while (y != 0){
            //ones that are present in both byte codes
            int digits = x & y;
            //sum but ones are not yet carried
            x = x ^ y;
            //shift until y == 0;
            y = digits << 1;
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

    public int multiply(int x, int y)
     {
        // YOUR CODE HERE
        if ((x == 0) || (y == 0)){
            return 0;
        }
        int xHolder = x;
        if (x < 0){
            xHolder = add(~x, 1); 
        }
        int yHolder = y;
        if (y < 0){
            yHolder = add(~y, 1);
        }
        int total = 0;
        int count = 0;
        while (count < yHolder){
            total = add(total, xHolder);
            count++;
        }
        if (((x < 0) && (y < 0)) || ((x > 0) && (y > 0))){
            return total;
        }else{
            return add(~total, 1);
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
    public void saveEquation(String equation, int result) 
    {
        stringOfEqs = new EquationList(equation, result, stringOfEqs);   
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public int getEqLength()
    {
        int lengthOfEqList = 0;
        EquationList helper1 = stringOfEqs;
        while(helper1 != null){
            lengthOfEqList++;
            helper1 = helper1.next;
        }
        return lengthOfEqList;
    }

    public void printAllHistory() 
    {
        int lengthOfEqList = getEqLength();
        printHistory(lengthOfEqList);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) 
    {
        EquationList helper = stringOfEqs;
        for(int i = 0; i < n; i++){
        String forTheGloriousCauseOfPrintingEquations = helper.equation;
        int theLastVariableWasTooDamnLong = helper.result;
        System.out.println(forTheGloriousCauseOfPrintingEquations + " = "  + theLastVariableWasTooDamnLong);
        helper = helper.next;
       }
    } 

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() 
    {
        stringOfEqs = stringOfEqs.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() 
    {
        stringOfEqs = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() 
    {
        int result = 0;
        EquationList helper = stringOfEqs;
        for( int i = 0; i < getEqLength(); i++){
            result += helper.result;
            helper = helper.next;
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
        // YOUR CODE HERE
        int result = 1;
        EquationList helper = stringOfEqs;
        for( int i = 0; i < getEqLength(); i++){
            result *= helper.result;
            helper = helper.next;
        }
        return result;
    }
}