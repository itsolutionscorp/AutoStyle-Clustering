import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = new EquationList(null, 0, null);
    public EquationList temp = history;
    public EquationList temp2 = history;
    public int hCount = 0;
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
        int first = x & y;
        int second = x | y;
            if (first == 0) {
                return second;
            } else if (second == 0){
                return first;
            } else if ((first ^ second) == 0) {
                return first << 1;
            } else {
                return add(first << 1, first ^ second);
            }

        
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
        int counter = 31;
        int prod = 0;
        int subtractor = -2147483648;
        while (counter >= 0){
            if (y >> counter != 0) {
                prod = add(prod, (x << counter));
                y = add(y, subtractor);
            }
            subtractor = subtractor >> 1;
            counter = counter - 1;
        }
        return prod;
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
        if(hCount == 0){
            hCount += 1;
        } else {
            temp2 = temp;
            temp.next = new EquationList(null, 0, null);
            temp = temp.next;
        }
        temp.equation = equation;
        temp.result = result;
        
        
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
        //EquationList temp2 = history;
        //while (temp2 != null && temp2.equation != null){
        //    System.out.println(temp2.equation + " = " + temp2.result);
        //    temp2 = temp2.next;
        int nCount = 0;
        EquationList temp3 = history;
        while (temp3 != null){
            nCount += 1;
            temp3 = temp3.next;
        }
        printHistory(nCount);
        
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
        int nCount = 0;
        int nCount2 = 0;
        int nCount3 = 0;
        int nCount4 = 0;
        EquationList temp3 = history;
        while (temp3 != null){
            nCount += 1;
            temp3 = temp3.next;
        }
        temp3 = history;
        while (nCount2 < n && nCount2 < nCount){
            nCount2 += 1;
            nCount3 = nCount4;
            while (nCount3 < nCount - 1){
                temp3 = temp3.next;
                nCount3 +=1;
            }
            if (hCount != 0){
                System.out.println(temp3.equation + " = " + temp3.result);
            }
            temp3 = history;
            nCount4 += 1;


        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        temp = temp2;
        temp.next = null;
        if (temp2.equation == null){
            hCount = 0;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = new EquationList(null, 0, null);
        temp = history;
        hCount = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        int nCount = 0;
        int nCount2 = 0;
        EquationList temp4 = history;
         while (temp4 != null){
            nCount += 1;
            temp4 = temp4.next;
        }
        temp4 = history;
        while (nCount2 < nCount){
            sum = sum + temp4.result;
            temp4 = temp4.next;
            nCount2 += 1;
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
        int prod = 1;
        int nCount = 0;
        int nCount2 = 0;
        EquationList temp4 = history;
         while (temp4 != null){
            nCount += 1;
            temp4 = temp4.next;
        }
        temp4 = history;
        while (nCount2 < nCount){
            prod = prod * temp4.result;
            temp4 = temp4.next;
            nCount2 += 1;
        }
        return prod;
        
    }
}