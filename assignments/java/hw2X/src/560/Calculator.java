import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
EquationList herstory;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
       int carrier;

        while (y != 0){
            carrier = x & y;
            x = x ^ y;
            y = carrier << 1;
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
        int resreturn = 0;

        if((x < 0) && (y < 0))
        {
            y = ~ y;
            x = ~x;
            y = add(y,1);
            x = add(x,1);

        while (y != 0)
        {
            if ((y & 1) != 0)
            {
                resreturn = add(resreturn,x);
            }
            x= x << 1;
            y = y >> 1;

        }
        }
        else{
            while (y != 0)
        {
            if ((y & 1) != 0)
            {
                resreturn = add(resreturn,x);
            }
            x= x << 1;
            y = y >> 1;

        }
        }
        return resreturn;
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
        if (herstory == null){
            herstory = new EquationList(equation, result, null);
        }
        else{
            herstory = new EquationList(equation, result, herstory);
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
        EquationList b = herstory;
        while (b != null){
            System.out.print(b.equation + " = ");
            System.out.println(b.result);
            b = b.next;
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
        int i = 1;
        EquationList b = herstory;
        while (b != null){
            if (i <= n){
                System.out.print(b.equation + " = ");
                System.out.println(b.result);
                b = b.next;
                i = i + 1;
            }
            else{
                b = b.next;
                i = i +1;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (herstory != null){
            herstory = herstory.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (herstory != null){
            herstory = herstory.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int summer = 0;
        if(herstory == null){
            return summer;
        }
        else{
            EquationList b = herstory;
            while (b != null){
                summer = summer + b.result;
                b = b.next;
            }
            return summer;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int prodigy = 1;
        if(herstory == null){
            return prodigy;
        }
        else{
            EquationList b = herstory;
            while (b != null){
                prodigy = prodigy * b.result;
                b = b.next;
            }
            return prodigy;
        }
    }
}