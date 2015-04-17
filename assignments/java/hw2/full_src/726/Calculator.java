import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList lst;
    public int numOfEquations;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add (int x, int y){
        while (y!=0){
            int carry = x & y;

            x = x^y;
            y = carry <<1;
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
        if (x== 0 || y == 0){ //The product of 0 is 0
            return 0;
        }

        else if (x== 1){  //The product of any number and 1 is the number itself
            return y;
        }

        else if(y==1){
            return x;
        }

        boolean isNegative = false;
        if((x<0) && (y<0)){  //Ensures that x and y are both positive
            x = add(~x,1);
            y = add(~y,1);
        }

        else if (x < 0) {  
            x = add(~x, 1);
            isNegative = true;            
        }

        else if (y < 0){
            y = add(~y, 1);
            isNegative = true;
        }

        int a = x;  //Saves the initial value of x  
        int result = 0;

        while (y!=0){
            if (y==1){
                break;
            }

            if ((y&1) !=0){
                result = add(result, x);
            }

            x = x << 1;
            y = y>> 1;
                
        }
        result = add(result, x);

        if (isNegative){
            return add(~result,1);
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

        if (lst == null){
            lst = new EquationList(equation, result, null);
        }

        else{
           lst= new EquationList(equation,  result, lst); 
        }
        
        numOfEquations+=1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(numOfEquations);
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
        String equation;
        int result;
        EquationList pointer = lst;

        while ((n>0) && (pointer != null)){
            equation = pointer.equation;
            result = pointer.result;
            System.out.println(equation + " = " + result);

            pointer = pointer.next;
            n--;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if(lst != null){
            if (lst.next!= null){
              lst = lst.next;
              numOfEquations-=1;  
            }

            else{
                lst = null;
                numOfEquations = 0;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        lst = null;
        numOfEquations = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (lst == null){
            return 0;
        }

        int sum = 0;
        EquationList pointer = lst;
        while (pointer != null){
            sum += pointer.result;
            pointer = pointer.next;
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
        if (lst == null){
            return 1;
        }

        int product = 1;
        EquationList pointer = lst;
        while (pointer != null){
            product *= pointer.result;
            pointer = pointer.next;
        }

        return product;

    }
}