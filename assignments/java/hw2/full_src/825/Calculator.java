import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList list;
    public int summ = 0;
    public int prod = 1;

    public Calculator(){
        list = new EquationList(null,0,null);
    }
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (y == 0){
            return x;
        }
        else{
            return add(x^y,(x & y) << 1);
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
        if (x ==0 || y ==0){
            return 0;
        }
        else if (x ==1){
            return y;
        }
        else if (y ==1){
            return x;
        }
        else{
            boolean neg = false;
            if (x<0 && y>0){
                x = -x;
                neg = true;
            }
            else if (y <0 && x >0){
                y = -y;
                neg = true;
            }
            else if (x<0 && y<0){
                x = -x;
                y = -y;
            }
        int res =0;
        while (y !=0){
            int z = y & 1;
            if (z !=0){
                res = add(res,x);
            }
            x<<=1;
            y>>=1;
        }
        return neg ? (-res) : res;
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
        this.list.equation = equation;
        this.list.result = result;
        EquationList newlist = new EquationList(null,0,null);
        newlist.next = this.list;
        this.list= newlist;
    }
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList history = this.list;
        while (list.next != null){
            System.out.println(history.equation + " = " + history.result);
            history=list.next;
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
        EquationList history = this.list;
        for (int i =0;i<n;i++){
            System.out.println(history.equation + " = " + history.result);
            history=list.next;
        }
    }    

    /**
     *  TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/

    public void undoEquation() {
        this.list = list.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (list.next != null){
            this.list=list.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList history = this.list;
        while (list.next != null){
            summ = summ + history.result;
            history = list.next;
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
        EquationList history = this.list;
        while (list.next != null){
            prod = prod * history.result;
            history = list.next;
        }
        return prod;
    }
}