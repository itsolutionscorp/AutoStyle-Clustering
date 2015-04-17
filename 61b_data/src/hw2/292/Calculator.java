import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList list;
    public int numEq;
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
        while (y != 0) {                        //while there is a carry              
                int carry = x & y;              //carry is common bits of x and y
                x = x ^ y;                      //sum x and y where one bit is not set
                y = carry << 1;                 //shift carry by one
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
        int result = 0;
        while (y != 0) {
                if ((y & 01) != 0) {
                    result = result + x;
                }
            x = x << 1;
            y = y >>> 1;
            }
        return result;
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
        EquationList equationSave = new  EquationList(equation, result, null);
        this.list = catenate(equationSave, this.list);
    }

    //catenate method definition modified lab

    public EquationList catenate(EquationList A, EquationList B) {
    if (A == null){
        this.numEq += 1;
        EquationList result = B;
        return result;
    }
    else if (B == null){
        this.numEq += 1;
        EquationList result = A;
        return result;
    }
    else {
        this.numEq += 1;
        EquationList result = new EquationList(A.equation, A.result, null);
        EquationList ptr = result;
        A = A.next;
        while(A != null){
            this.numEq += 1;
            ptr.next = new EquationList(A.equation, A.result, null);
            A = A.next;
            ptr = ptr.next;
        }
      
        while(B != null){
            this.numEq += 1;
            ptr.next = new EquationList(B.equation, B.result, null);
            B = B.next;
            ptr = ptr.next;
        }
        return result;
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
        printHistory(numEq);
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
        if ((this.list) != null) {
            int i = 0;
            EquationList equationPrint = new EquationList(this.list.equation, this.list.result, this.list.next);
            while((equationPrint != null) && (i < n)) {
                System.out.println(equationPrint.equation + " " + "=" + " " + equationPrint.result);
                equationPrint = equationPrint.next;
                i += 1;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE 
        if ((this.list.next != null)) {
            EquationList result = new EquationList(this.list.next.equation, this.list.next.result, this.list.next.next);
            numEq -= 1;
            this.list = result; 
        }
        else {
            this.clearHistory();
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        this.list = null;
        this.numEq = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if ((this.list) != null) {
            EquationList temp = this.list;
            int cSum = temp.result;

            while(temp.next != null){
                temp = temp.next;
                cSum += temp.result;
            }
            return cSum;
        }
        else {
            return 0;
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
        if((this.list) != null){
            EquationList temp = this.list;
            int cProduct = temp.result;

            while(temp.next != null){
                temp = temp.next;
                cProduct *= temp.result;
            }
            return cProduct; 
        }
        else {
            return 0;
        }
    }
}