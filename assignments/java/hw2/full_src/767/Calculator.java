import list.EquationList;

public class Calculator {

    //create the variable front to store the equations;
    public EquationList front;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (x!=0){
            int and = x & y;
            y = y ^ x;
            and = and << 1;
            x = and; 
        }
        return y;
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

        // if y is negative, flip the sign of x and y;
        if (y < 0){
            y = ~y+ 1;
            x = ~x+ 1;
        }

        int result = 0;
        while (y!=0){
            if ((y & 01)!=0) {
                result = add (result, x);
            }
            x = x << 1;
            y = y >> 1;
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
        front = new EquationList(equation, result, front);
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (front != null){
            EquationList L = front;
            while (L!= null){
                System.out.print(L.equation);
                System.out.print(" = ");
                System.out.println(L.result);
                L=L.next;
            }
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
        //check if n is positive
        if (n>0) {
            //check if the equationlist is not null
            if (front != null){    
                EquationList L = front;
                while (n>1 && L.next != null){ 
                    n = n -1;
                    L= L.next; 
                }

                //check if n is actually larger than the equation list size
                if (n == 1){
                    System.out.print(L.equation);
                    System.out.print(" = ");
                    System.out.println(L.result);
                }
                else{
                    System.out.println ("exceeds history range");
                }
            }
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        front = front.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        front = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (front == null){
            return 0;
        }
        else{
            EquationList L = front;
            int sum = 0;
            while (L!= null){
                sum = add(sum, L.result);
                L = L.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
       if (front == null){
            return 1;
        }
        else{
            EquationList L = front;
            int product = 1;
            while (L!= null){
                product = multiply(product, L.result);
                L = L.next;
            }
            return product;
        }
    }
}