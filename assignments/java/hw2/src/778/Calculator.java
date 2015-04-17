import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList lst;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int xor = x ^ y;
        int and = x & y;
        while (and>0){
            int tempAnd = and << 1;
            int tempXor = xor;
            xor = tempXor ^ tempAnd;
            and = tempXor & tempAnd;
        }
        return xor;
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
        // if (y == 0){
        //     return 0;
        // }
        // int temp = y;
        // int power = 0;
        // while (temp>1){
        //     temp = temp >>> 1;
        //     power = add(power, 1);
        // }
        // int powerOfTwo=1<<power;
        // int solution=x << power;

        // return add(solution, multiply(x, add(y, add(~powerOfTwo,1))));
        Boolean negative = false;
        if (x<0){
            x = add(~x, 1);
            negative = true;
        }
        if (y<0){
            y = add(~y, 1);
            negative = !negative;
        }
        int sum = 0;
        int counter = 0;
        while (counter < 32){
            int oneOrZero = y & 1;

            if (oneOrZero == 1){
                int temp = x << counter;
                sum = add(sum, temp);
            }

            y = y >>> 1;
            // System.out.println(y);
            counter +=1;
        }
        if (negative){
            return add(~sum, 1);
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

        lst= new EquationList(equation, result, lst);
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = lst;
        while (temp != null){
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
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
        EquationList temp = lst;
        while (n>0 && temp != null){
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        lst = lst.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        lst = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = lst;
        int sum = 0;
        while (temp != null){
            sum = add(sum, temp.result);
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
        EquationList temp = lst;
        int product = 1;
        while (temp != null){
            product = multiply(product, temp.result);
            temp = temp.next;
        }
        return product;
    }
}