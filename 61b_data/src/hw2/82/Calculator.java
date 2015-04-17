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
        // YOUR CODE HERE
    	int alpha = x ^ y;
    	int beta = (x & y)<<1;
    	if (beta == 0){
    		return alpha;
        }
    	else{
            return add(alpha,beta);
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
        int soln=0, xt=x,yt=y;
        if(yt < 0){
            yt=add(~yt,1);
        }
        while((yt != 0 && yt != -1)){
            if((yt & 1) != 0){
                soln = add(soln,xt);
            }
            xt = xt << 1;
            yt = yt >> 1;
        }
        if (y < 0){
            return add(~soln,1);
        }
        return soln;
        //11 * 11 = 1001
        //11 * 10 = 0110
        //10 * 10 = 0100
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
    public EquationList equations;
    public void saveEquation(String equation, int result) {
        EquationList newEq= new EquationList(equation,result,equations); 
        equations = newEq;
        
    }
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp=equations;
        int i = 1;
        while (temp != null){
            i++;
            temp=temp.next;
        }
        printHistory(i);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temporary = equations;
        for(int i =n;i>0;i--){
            if(temporary == null){
                return;
            }
            System.out.println(temporary.equation+" = "+ temporary.result);
            temporary = temporary.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(equations == null){
            return;
        }
        else{
            equations=equations.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while(!(equations == null)){
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
        int summ = 0;
        EquationList temporary = equations;
        while(temporary!=null){
            summ += temporary.result;
            temporary=temporary.next;
        }
        return summ;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int prod=1;
        EquationList temporary = equations;
        while(temporary!=null){
            prod *= temporary.result;
            temporary=temporary.next;
        }
        return prod;
    }
}
