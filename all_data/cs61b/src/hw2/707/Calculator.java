import list.EquationList;

public class Calculator {
    private EquationList eqList;
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
        // YOUR CODE HERiE
        while (y != 0) {
            int carryBit = x & y;
            x = x ^ y; 
            y = carryBit << 1;
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
        Calculator calc = new Calculator();
        int product = 0;
        while(y != 0) {
            if ((y & 1) != 0)
                product = calc.add(product,x);
            x = x << 1;
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
        // YOUR CODE HEREi
        EquationList temp = new EquationList(equation,result,null);
        temp.next = eqList;
        eqList = temp; 
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
        EquationList location = this.eqList; 
        //if (eqList == null)
        //    System.out.println("");
        while(location != null) {
            System.out.println(location.equation + " = " + location.result);
            location= location.next;
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
        EquationList location = this.eqList;
       // if (eqList == null)
       //     System.out.println("");
            while(n > 0) {
                if (location.next == null) //the case that a user enters an n larger than the number of eqns in history.
                   n -=n;
                System.out.println(location.equation + " = " + location.result);
                location = location.next;
                n--; 
            }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
       this.eqList = this.eqList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        this.eqList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        Calculator calc = new Calculator();
        EquationList list = this.eqList;
        if (list == null)
            return 0;
        int sum = 0;
        while(list != null){
            sum = calc.add(sum,list.result);
            list  = list.next;
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
        Calculator calc = new Calculator();
        EquationList list = this.eqList;
        if (list == null)
            return 1;
        int product = list.result;
        list = list.next;
        while((list != null)){
            product = calc.multiply(product,list.result);
            list  = list.next;
        }  
        return product;
    }
}
