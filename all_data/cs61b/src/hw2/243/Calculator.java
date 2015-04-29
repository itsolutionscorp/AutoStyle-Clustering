import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equList;  
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

public int add(int x, int y) {

    int sum = 0; 
    int carry = 0; 
    int shift = 1; 
    if (x == 0) {
        return y; 
    }
    else if (y == 0) {
        return x; 
    }
    for (int i=1; i<32; i++) {
        int xdig = x & shift; 
        int ydig = y & shift; 

        if (xdig != ydig) {
            if (carry == 0) {
                sum = sum | xdig | ydig; 
            }
        }
        else {
            if (xdig == 0) {
                sum = sum | carry; 
                carry = 0; 
            }
            else {
                sum = sum | carry; 
                carry = shift; 
            }
        }
        carry = carry << 1; 
        shift = shift << 1; 
}         
            return (sum << 1) >> 1; 
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

        int sum = 0;
        int shift = 1; 
        int xdig = x; 
        for (int i=1; i<32; i++) {
            int ydig = y & shift; 
            if (ydig != 0) {
                sum = add(sum, xdig);
            }
            xdig = xdig << 1; 
            shift = shift << 1; 
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
        if (equList == null) {
            equList = new EquationList(equation, result, null); 
        }
        else {
            EquationList i = new EquationList(equList.equation, 
                equList.result, equList.next); 
            equList = new EquationList(equation, result, i); 
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
        printHistory(1000000000); 
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int count = 0; 
        EquationList sub = equList; 
        while (count < n) {
            if (sub == null) {
                break; 
            }
            System.out.println(sub.equation + " = " + sub.result); 
            sub = sub.next; 
            count = count + 1; 
        }
        return; 
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equList = equList.next; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equList = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0; 
        EquationList temp = equList; 
        while (temp != null) {
            sum = sum + temp.result; 
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
        EquationList temp = equList; 
        while (temp != null) {
            product = product * temp.result; 
            temp = temp.next; 
        }
        return product; 
    }
}