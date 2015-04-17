import list.EquationList;

public class Calculator {
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
        int counter = 0;
        int moved = x;
        int or = y;
        int temp = 0;
        while (counter < 32){
            temp = moved & or;
            or = moved ^ or;
            moved = temp << 1;
            counter += 1;
        }
        return or;
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
        if (x<0 && y >0) {
            return negAndPos(x, y);
        }
        else if (y<0 && x>0){
            return negAndPos(y, x);
        }
        else if (x<0 && y<0){
            x = changeSign(x);
            y = changeSign(y);
            return multiplying(x,y);
        }
        else{
            return multiplying(x,y);
        }
    }

    public int negAndPos(int x, int y){
        int product = 0;    
        x = changeSign(x);
        product = multiplying(x,y);
        product = changeSign(product);
        return product;
    }

    public int changeSign(int x){
        x = ~x;
        x = add(x,1);
        return x;
    }

    public int multiplying(int x, int y){
        int counter = 0;
        int sum = 0;
        while(counter < y){
            sum = add(sum,x);
            counter +=1;
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

    public EquationList history = null; 
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        history = new EquationList(equation, result, history);
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
        EquationList temphistory = history;
        while(temphistory != null){
            System.out.println(temphistory.equation + " = " + Integer.toString(temphistory.result));
            temphistory = temphistory.next;
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
        EquationList temphistory = history;
        while(n>1){
            temphistory = temphistory.next;
            n -=1;
        }
        System.out.println(temphistory.equation + " = " + temphistory.result);

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temphistory = history;
        while(temphistory != null){
            sum += temphistory.result;
            temphistory = temphistory.next;
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
        EquationList temphistory = history;
        while(temphistory != null){
            product = product * temphistory.result;
            temphistory = temphistory.next;
        }
        return product;
    }
}