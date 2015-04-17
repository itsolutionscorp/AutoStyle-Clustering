import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history=null;
    int historylength=0;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry=0;
        int sum=0;
        for (int i=0; i < 32; i++){
            int xbit=getBit(x,i);
            int ybit=getBit(y,i);
            if ((xbit|ybit) == 0){ // 0+ 0 case
                if (carry==1){
                    sum=setBit(sum, i);
                    carry=0;
                }
            }
            else{ 
                if ((xbit & ybit)==1){ // 1+1 case
                    if (carry==1){
                        sum=setBit(sum, i);
                    }
                    carry=1;
                }
                else { // 0+1 case
                    if (carry==0){
                        sum=setBit(sum,i);
                    }else{ 
                        carry=1;
                    }
                }
            }
        }
        return sum;
    }
    public int getBit(int x, int i){
        // isolate the bit
        for (int j=i; j<31;j++){
            x=x<<1;
        }
        for (int j=1; j<32;j++){
            x=x>>>1;
        }
        return x;
    }
    public int setBit(int x, int i){
        int check= 1<< i;
        return x | check;
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
        int multiplied=0;
        int sum=0;
        for (int i=0; i<32;i++){
            int multiplier=getBit(x,i);
            for (int j=0; j<32; j++){
                int multiplier2=getBit(y,j);
                if ((multiplier2 & multiplier)==1){ //1*1 is the only case you get nonzero
                    multiplied=setBit(multiplied,j);
                } 
            }
            multiplied=multiplied<<i;
            sum=add(sum,multiplied);
            multiplied=0;

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
    public void saveEquation(String equation, int result) {
        history=new EquationList(equation, result, history);
        historylength++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(historylength);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int i=1;
        EquationList x = history; 
        while (i<=n && x!=null){
            System.out.println(x.equation+" = "+x.result);
            x=x.next;
            i++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history=history.next;
        historylength -=1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history= null;
        historylength=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum=0;
        int i=0;
        EquationList x= history;
        while (i<historylength && x!=null){
            sum+=x.result;
            x=x.next;
            i++;
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
        int i=0;
        EquationList x= history;
        while (i<historylength && x!=null){
            product*=x.result;
            x=x.next;
            i++;
        }
        return product;
    }
}