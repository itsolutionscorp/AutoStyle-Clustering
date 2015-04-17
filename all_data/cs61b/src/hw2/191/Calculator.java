import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eqnList = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    // and = getting the last digit, xor = add, and = carrying, shifting left once to get the next digit
    public int add(int x, int y) { 
        int sum = x ^ y; // without carrying
        int carry = x & y; // finds out what you need to carry
        while (carry != 0){
            carry = carry<<1; // shifts 1 over because you're carrying
            int tempSum = carry ^ sum; // repeats adding process using a tempsum
            carry = carry & sum;
            sum = tempSum;
        }
        return sum;
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
        int product = 0;
        int i = 0;
        int temp = 0;
        Calculator adder = new Calculator();

        while (y != 0){
            int currDigitY = y & 1;
            if (currDigitY == 1){
                temp = x << i;
            } else {
                temp = 0;
            }
            product = adder.add(product, temp);
            i += 1;
            y = y >>> 1;      

        }            
        return product;
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
        eqnList = new EquationList(equation, result, eqnList);
    }   

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (eqnList != null){
            printHistory(1);
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
        for (int i=0; i<n; i++){
            if (eqnList == null){
                return;
            }
            System.out.println(eqnList.equation + " = " + eqnList.result);
            eqnList = eqnList.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqnList = eqnList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqnList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList eqnListDummy = eqnList;
        if (eqnListDummy == null){
            return sum;
        } else {
            while (eqnListDummy != null){
                sum += eqnListDummy.result;
                eqnListDummy = eqnListDummy.next;
            }
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
        EquationList eqnListDummy = eqnList;
        if (eqnListDummy == null){
            return product;
        } else {
            while (eqnListDummy != null){
                product = product * eqnListDummy.result;
                eqnListDummy = eqnListDummy.next;
            }
        }
        return product;
    }

}