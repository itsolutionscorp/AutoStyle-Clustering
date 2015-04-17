import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history=null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int rawSum=x^y; //only adds bits that are 0 in one and 1 in the other
        int overlap=x&y; //stores bits that are both one. Arithmetically summing, these are the ones that get carried over
        int newOverlap=overlap; //pointer for loop to work
        
        while (newOverlap!=0){
            overlap=overlap<<1; // bits that need be carried over. Carried over by shifting one bit to the left
            newOverlap=rawSum&overlap; //if overlap happens again, store bits that need to be carried over
            rawSum=rawSum^overlap;
            overlap=newOverlap;
        }
        return rawSum;
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
        /*
         * Bitwise integer multiplication can be seen like this. Say x=7 and y=13
         * y in binary is 1101, or 1*2^3 + 1*2^2 + 0*2^1 + 1*2^0
         * We know we can raise x to a power of two by a leftward shift of 1.
         * That means that if you multiply x by 2^k power, weighted by the binary
         * digits of y, and sum those up, the result is the final multiplication of
         * x and y.
        */
        int answer=0;
        while (y!=0){
            if ((y&1) != 0){ // y&1 checks if there's a 1 on the zeroth bin digit of y
                answer=answer+x;
            }
            y=y>>>1;
            x=x<<1;
        }
        return answer;
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
        history= new EquationList(equation,result,history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList a=this.history;
        while (a!=null){
            System.out.println(a.equation+" = " + Integer.toString(a.result));
            a=a.next;
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
        EquationList a=this.history;
        while (n>0){
            if (a==null){
                return;
            }
            System.out.println(a.equation+" = " + Integer.toString(a.result));
            a=a.next;
            n-=1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history==null){
            return;
        }
        else{
            history=history.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history=null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList a = history;
        int tally=0;
        while (a!=null){
            tally+=a.result;
            a=a.next;
        }
        return tally;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList a = history;
        int tally=1;
        while (a!=null){
            tally*=a.result;
            a=a.next;
        }
        return tally;
    }
}