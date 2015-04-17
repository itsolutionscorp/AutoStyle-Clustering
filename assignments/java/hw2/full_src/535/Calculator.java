import list.EquationList;

public class Calculator {
    public EquationList eqns = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    //pseudocode borrowed from wikipedia
    public int add( int x, int y){
        int carry;
        while(y!=0){
            carry=x & y;
            x= x ^ y;
            carry=carry << 1;
            y=carry;
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
        if(x>0 && y>0){
            int result=0;
            int i=0;
            while(i<y){
                result=add(result,x);
                i=i+1;
            }
            return result;
        }
        else if(x<0 && y<0){
            x=negate(x);
            y=negate(y);
            return multiply(x,y); 
        }
        
        else if(x<0){
            x=negate(x);
        }
        else{
            y=negate(y);
        }
        return negate(multiply(x,y));
    }
    public int negate(int x){
        x= ~x;
        x=add(x,1);
        return x;
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
        eqns=new EquationList(equation, result, eqns);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp=eqns;
        while (eqns!=null){
            printHistory(1);
            eqns=eqns.next;
        }
        eqns=temp;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp = eqns; 
        for(int i=0; i<n; i++){
            if(temp==null){
                break;
            }
            System.out.println(temp.equation+" = "+temp.result);
            temp=temp.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqns= new EquationList(eqns.next.equation, eqns.next.result,
            eqns.next.next);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqns= null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
       EquationList temp = eqns;
       int sum=0;
       while(temp!=null){
            sum+=temp.result;
            temp=temp.next;
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
        EquationList temp = eqns;
        int product=1;
        while(temp!=null){
            product*=temp.result;
            temp=temp.next;
        }
        return product;
    }
}