import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    String history;
    int result;
    EquationList e = null;
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
        //return x + y;

        //if ends with 1, result from adding 1 is
        //ends with 0. otherwise ends with 1

        //check if ends with 1
        while (x != 0 && y != 0){
            if (x > 0){
                x = subtractOne(x);
                y = addOne(y);
            }
            else{
                y = subtractOne(y);
                x = addOne(x);
            }
        }

        if (x == 0){
            return y;
        }
        return x;


        // int count = 0;
        // int answer = 0;
        // while (count < y){
        //     answer += addOne(x);
        //     count+=1;
        // }
        // return answer;

    }


    public int subtractOne(int x){
        boolean done = false;
        int flag = 1;
        while (!done){
            int condition = x & flag;
            if (condition != 0){
                x = x ^ flag;
                done = true;
            }
            else {
                x = x | flag;
                flag = flag << 1;
            }
        }
        return x;
    }
    public int addOne(int x){
        boolean done = false;
        int flag = 1;
        while(!done){
            int condition = x & flag;
            if (condition == 0){
                x = x | flag;
                done = true;
            }
            else{
                x = x ^ flag;
                flag = flag << 1;
            }
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
        // 001
        ///11100
        //0 + 111 + 1110 + 11100
        int res = 0;
        while (x != 0){
            if ((x & 1) != 0){
                res = add(res, y);
            }
            x = x >>> 1;
            y = y << 1;
        }
        return res;
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
        e = new EquationList(equation,result,e);
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
        printHistory(Integer.MAX_VALUE);

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
        EquationList cursor = e;
        for(int i = 0; i < n; i++){
            if(cursor != null){
                System.out.println(cursor.equation + " = " + cursor.result);
                cursor = cursor.next;
            }
            else{
                return;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        // EquationList cursor = e;
        // while (cursor.next!=null) {
        //     cursor.equation = cursor.next.equation;
        //     cursor.result = cursor.next.result;
        //     if (cursor.next.next == null)
        //         cursor.next = null;
        //     else
        //         cursor.next = cursor.next.next;
        //     cursor = cursor.next;
        // }
        e = e.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (e!=null){
            undoEquation();
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int cumulSum = 0;
        EquationList cursor = e;
        while (cursor != null){
            cumulSum = add(cumulSum, cursor.result);
            cursor = cursor.next;
        }
        return cumulSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int cumulProd = 1;
        EquationList cursor = e;
        while (cursor != null){
            cumulProd = multiply(cumulProd, cursor.result);
            cursor = cursor.next;
        }
        return cumulProd;
    }
}