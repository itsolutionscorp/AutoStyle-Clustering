import list.EquationList;

public class Calculator {
    EquationList previous=null;
    int numberEquations = 0;

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
        // YOUR CODE HERE
        if ((x & y) == 0){
        	return (x ^ y);
        }else{
        	return add( (x & y) << 1, (x ^ y));
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
        if (x==0 || y==0){
            return 0;
        } else if (x<0 && y<0){
            return multiply(add((~x),1),add((~y),1));
        } else if (x<0 && y>0){
            return add((~multiply(add((~x),1),y)),1);
        } else if (x>0 && y<0){
            return add((~multiply(add((~y),1),x)),1);
        } else{
            if (y==1){
                return x;
            } else{
                if ((y & 1)==1){
                    return add(x, multiply((x<<1),(y>>1)));
                } else{
                    return multiply((x<<1),(y>>1));
                }
            }
        }
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
        previous = new EquationList(equation, result, previous);
        numberEquations += 1;
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
        printHistory(numberEquations);
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
        int index=0;
        EquationList i = previous;
        while (index < n && i != null){
            System.out.println(i.equation + " = " + i.result);
            i = i.next;
            index += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (previous != null){
            previous = previous.next;
            numberEquations -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        previous = null;
        numberEquations = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (previous == null){
            return 0;
        } else{
            EquationList i = previous;
            int sum = 0;
            while (i != null){
                sum += i.result;
                i = i.next;
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
        // YOUR CODE HERE
        if (previous == null){
            return 1;
        } else{
            EquationList i = previous;
            int pro = 1;
            while (i != null){
                pro *= i.result;
                i = i.next;
            }
            return pro;
        }
    }
}