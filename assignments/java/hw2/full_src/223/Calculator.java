import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList history = null; 
    public int history_len = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = 0;
        for(int place = 1; (x != 0) || (y != 0); place <<= 1){
            int A = x & place;
            int B = y & place;
            x ^= A;
            y ^= B;
            int addition = (((A & B) << 1) | (A ^ B));
            if(((A ^ B) != 0) && ((sum & place) != 0)){
                addition = place << 1;
                sum = sum ^(sum & place);
                }
            sum = sum | addition;
            } 
        return sum;
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
        if(y >= 0) {
            return positiveMul(x,y);
        } else {
            return add(~positiveMul(x,add(~y,1)),1);
        }
    }

    private int positiveMul(int x, int y) {
        int res = 0;
        for (int place = 1; y != 0; place <<= 1) {
                int CxP = y & place;
                y ^= CxP;
                if (CxP != 0){
                    int Cxn = x;
                    for(CxP >>= 1; CxP != 0; CxP >>= 1){
                        Cxn =  Cxn << 1;
                    }
                    res = add(res, Cxn);
                }
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
        history = new EquationList(equation,result,history);
        history_len += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(history_len);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList P = history;
        int len = (n <= history_len)? n: history_len;
        for(int i = 0; i < len; i++){
            System.out.print(P.equation + " = " + P.result +"\n");
            P = P.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(history_len > 0){
            history_len -= 1;
            history = history.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        history_len = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList P = history;
        for(int i = 0; i < history_len; i++) {
            sum += P.result;
            P = P.next;
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
        EquationList P = history;
        for(int i = 0; i < history_len; i++) {
            product *= P.result;
            P = P.next;
        }
        return product;
    }
}