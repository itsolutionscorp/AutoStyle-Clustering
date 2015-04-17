import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;
    public boolean flag=true;
    public int printcount=0;
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
        int tempx=x;
        int tempy=y;
        while (y!=0)
        {
            int carry =x&y;
            x=x^y;
            y=carry<<1;
        }
        saveEquation(tempx+"+"+tempy,x);
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
        flag=false;
        int result=0;
        if(y>0){
        for (int counter=0;counter<y;counter++)
        {
            result=add(x,result);
        }
    }
    else
    {
        for (int counter=y;counter<0;counter++)
        {
            result=add(x,result);
        }
    }
        if(((x<0 && y>0)||(x>0 && y<0))&& result>0)
        {
            result= add(1,~result);
        }
        if(((x<0 && y<0)||(x>0 && y>0)) && result<0)
        {
            result= add(1,~result);
        }
        flag=true;
        saveEquation(x+"*"+y,result);
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
        // YOUR CODE HERE
        if (flag)
        {
            printcount+=1;
            history=new EquationList(equation,result,history);
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
        // YOUR CODE HERE
        EquationList temp=history;
        if (temp==null)
            return;
        while (temp.next!=null)
        {
            System.out.println(temp.equation+" = "+temp.result);
            temp=temp.next;
        }
        System.out.println(temp.equation+" = "+temp.result);
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
        EquationList temp1=history;
        if (temp1==null)
            return;
        if(n>printcount)
        {
            return;
        }
        while (n>0)
        {
            System.out.println(temp1.equation+"="+temp1.result);
            temp1=temp1.next;
            n=n-1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history=history.next;
        printcount-=1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history=null;
        printcount=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int total=0;
        EquationList temp=history;
        while (temp.next!=null)
        {
            total+=temp.result;
            temp=temp.next;
        }
        return total+temp.result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int total=1;
        EquationList temp=history;
        while (temp.next!=null)
        {
            total=total*temp.result;
            temp=temp.next;
        }
        return total*temp.result;
    }
}