import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqList;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carryDigit = 0; //updated every loop
        int sum = 0; //calculated from the left side, updated every loop
        for (int i=0; i<32; i=i+1){
            int xDigit = x & (1<<i); //mask all the other digits
            int yDigit = y & (1<<i);
            int sumDigit = xDigit ^ yDigit ^ carryDigit; 
            sum = sum ^ sumDigit; //update sum
            //calculate the carryDigit for next loop
            if ((xDigit==(1<<i)) && (yDigit==(1<<i)) 
                || (xDigit==(1<<i)) && (carryDigit==(1<<i))
                || (carryDigit==(1<<i)) && (yDigit==(1<<i))){
                    carryDigit=(1<<(i+1));
            } else{
                    carryDigit = 0;
            } 
        }
        //try to test if result overflow
        /* 
         *if ((x>>>31)==1 && (y>>>31)==1 && (sum>>>31)==0 ||
         *   (x>>>31)==0 && (y>>>31)==0 && (sum>>>31)==1){
         *  System.out.println("sum overflow!");
         * return -1;
         *} else {
         */
        return sum;
        //}
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
        int product=0;
        for (int i=0; i<32; i=i+1){
            if ((y & (1<<i)) == (1<<i)){
                product=add(product,(x<<i));
            }
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
        EquationList temp = eqList;
        eqList = new EquationList(equation, result, temp);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = eqList;
        int n=0;
        while (temp!=null){
            n=n+1;
            temp=temp.next;
        }
        printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp = eqList;
        for (int i=0; i<n; i=i+1){
            if (temp==null){
                break;
            }
            System.out.println(temp.equation + " = " + temp.result);
            temp=temp.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqList=eqList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList=null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temp = eqList;
        while (temp != null){
            sum = add(sum,temp.result);
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
        EquationList temp = eqList;
        while (temp != null){
            product = multiply(product,temp.result);
            temp = temp.next;
        }
        return product;
    }
}