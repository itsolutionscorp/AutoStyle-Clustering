import list.EquationList;

public class Calculator {
    EquationList a;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int and = x & y;
        int flip = x ^ y;
        while (and != 0){
            int a = and << 1;
            and = a & flip;
            flip = flip ^ a;
        }
        flip = and ^ flip;
        return flip;
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
        int a = 0;
        while (x!=0){
            if ((x & 1) != 0){
                a = a+y;
            } 
            x= x>>>1;
            y= y<<1;
        }
        return a;
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
        if(a == null){
            a = new EquationList(equation, result, null);
            return;
        }
        EquationList temp = a;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new EquationList(equation,result,null);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = a;
        int count = 0;
        while (temp != null){
            temp = temp.next;
            count = count + 1;
        }
        for (int i = 1; i <= count; i++){
            printHistory(i);
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
        EquationList temp = a;
        EquationList temp2 = a;
        int count = 0;
        while (temp != null){
            temp = temp.next;
            count = count + 1;
        }
        int count2 = count - n + 1;
        for (int i = 1; i < count2; i++){
            temp2 = temp2.next;
        }
        System.out.println(temp2.equation + " = " + temp2.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList temp = a;
        if(a.next == null){
            a = null;
            return;
        }
        while(temp.next.next != null){
            temp = temp.next;
        }  
        temp.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        a = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = a;
        int sum = 0;
        while (temp != null){
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
        EquationList temp = a;
        while (temp != null){
            product = product * temp.result;
            temp = temp.next;
        }         
        return product;
    }
}