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
    private EquationList eql;
    private int size;
    public Calculator(){
        eql=null;
        size=0;
    }
    public int add(int x, int y) {
        // YOUR CODE HERE
        // int mask=(x&y)<<1;
        // int flip=x^y;
        // for (int i=0;i<y;i++){
        //     int mask1=mask;
        //     int flip1=flip;
        //     mask=(mask1&flip1)<<1;
        //     flip=mask1^flip1;
        // }
        // return mask^flip;
        int x_end= x&1;
        int y_end= y&1;
        int x_rest= x>>>1;
        int y_rest= y>>>1;
        int carry=0;
        int total=0;
        int result=0;
        for (int i=0; i< 32; i++){
            result= (x_end^y_end) ^carry; 
            carry= (x_end & y_end) | ((x_end & carry) | (y_end & carry));
            total= (result<<i)^ total;
            x_end= x_rest & 1;
            y_end= y_rest & 1;
            x_rest= x_rest>>>1;
            y_rest= y_rest>>>1;
        }
        return total;
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
        int total=0;
        int y_end= y&1;
        int y_rest=y>>>1;
        for (int i =0; i<32; i++){
            if (y_end==1){
                total=add(total,x);
            }
            x=x<<1;
            y_end=y_rest &1;
            y_rest=y_rest>>>1;
        }
        return total;
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
        eql= new EquationList(equation, result, eql);
        size+=1;
    }
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp_eql=eql;
        while (temp_eql!=null){
            if (n==0){
                return;
            }
            System.out.println(temp_eql.equation + " = " + Integer.toString(temp_eql.result));
            temp_eql=temp_eql.next;
            n-=1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eql=eql.next;
        size-=1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eql=null;
        size=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total=0;
        EquationList temp_eql=eql;
        while (temp_eql!=null){
            total+=temp_eql.result;
            temp_eql=temp_eql.next;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int total=1;
        EquationList temp_eql=eql;
        while (temp_eql!=null){
            total*=temp_eql.result;
            temp_eql=temp_eql.next;
        }
        return total;
    }
}