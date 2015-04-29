import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int z=1;
        while(z!=0)
        {
            z=x&y;
            z=z<<1;
            x=x^y;
            y=z;
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
        if(y==0){return 0;}
        int total=0;
        int counter=1;
        while(y!=0){
            if((y&1)==1)
            {
                total=add(total,x<<(add(counter,-1)));
            }
            y=y>>>1;
            counter=add(counter,1);
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
    public static EquationList getLast(EquationList curr){
        if (curr==null)
            {return null;}
        else if (curr.next==null)
            {return curr;}
        return Calculator.getLast(curr.next);
    }

    public void saveEquation(String equation, int result) {
        if (Calculator.getLast(history)==null)
            {history=new EquationList(equation,result,null);}
        else{
        Calculator.getLast(history).next=new EquationList(equation,result,null);}
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public int retLen()
    {
        EquationList curr=history;
        int retr=0;
        //if(curr==null){return 0;}
        while(curr!=null)
            {
            retr+=1;
            curr=curr.next;
            }
        return retr;
    }

    public EquationList retN(int n)
    {
        EquationList curr=history;
        if (n>retLen()){
            return getLast(curr);
        }
        for(int i=0;i<n;i++){
            curr=curr.next;
        }
        return curr;
    }

    public void printAllHistory() {
        printHistory(retLen());
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printHistory(int n) {
        EquationList curr;
        for(int i=0;i<n;i++)
        {
            curr=retN(n-i-1);
            System.out.println(curr.equation+" = "+curr.result); 
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history==null){return;}
        EquationList curr;
        curr=retN(retLen()-2);
        curr.next=null;
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
        EquationList curr=history;
        int total=0;
        while (curr!=null)
        {
            total+=curr.result;
            curr=curr.next;
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
        EquationList curr=history;
        int total=1;
        while (curr!=null)
        {
            total=total*curr.result;
            curr=curr.next;
        }
        return total;
    }
}