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
    int numE=0;
    EquationList first;
    public int add(int x, int y) {
        int a= x^y;
        int b =(x & y)<<1;
        if((a & b)==0){
            return (a ^ b);
        }else{
            return add(a, b);
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
        int a = x;
        int b = y;
        if(a==0 || b==0){
            return 0;
        }else if(a==1){
            return b;
        }else if(b==1){
            return a;
        }else if((b&1)==0){
            return (multiply(a, b>>>1))<<1;
        }else {
            return add(a, (multiply(a,b>>>1)<<1) );
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
        if(numE==0){
            EquationList previous = null;
            first = new EquationList(equation, result, previous);
            numE=numE+1;
        }else{
            EquationList previous= first;
            first = new EquationList(equation, result, previous);
        numE=numE+1;
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
        this.printHistory(numE);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList move= first;
        int count= 0;
        while (count<n){
            if( move == null){
                break;
            }
            System.out.println(move.equation+" = "+move.result);
            move=move.next;
            
            count=count+1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        first= first.next;
        numE=numE-1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        first=null;
        numE=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum=0;
        int count=0;
        EquationList move=first;
        while(count<numE){
            sum=sum+move.result;
            move=move.next;
            count=count+1;
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
        int product=1;
        int count=0;
        EquationList move= first;
        while(count<numE){
            product=product*move.result;
            move=move.next;
            count=count+1;
        }
        return product;
    }
}