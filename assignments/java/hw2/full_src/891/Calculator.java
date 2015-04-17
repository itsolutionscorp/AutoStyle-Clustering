import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList listHead=null;
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
        int carry=y;
        int result=x;
        int oldresult=x;
        while (carry!=0) {

            result=oldresult^carry;
            carry=((~result)&oldresult)<<1;
            oldresult=result;

        }
        return result;
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
        // do the binary algorithm iteratively (recursive version exists)
        int count=y;
        boolean flag=false;
        int result=0;
        int current=x;

        if (count==0 || current==0){
            return 0;
        }



        if (count<0) {
            flag=true;
            count=add(0,-count);
        }

        while (count!=0) {
            if ((count&1)==0) { // if count is even
                current=add(current,current);
                count=count>>1;
            }
            else {  // if count is odd
                result=add(result,current);
                count=add(count,-1);
            }
        }
        if (flag) {
            return add(0,-result);
        }
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
        EquationList newHistory = new EquationList(equation,result,null);
        
        newHistory.next=this.listHead;
        this.listHead=newHistory;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (this.listHead==null) {
            System.out.println("History Empty");
            return;
        }

        EquationList node=this.listHead;

        int count=1;

        while (node!=null && count<n) {
            node=node.next;
            count+=1;
        }

        if (node==null) {
            System.out.println("History length less than " + n + "!");
            return;
        }

        System.out.println(node.equation + " = " + node.result);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (this.listHead==null) {
            return;
        }

        EquationList node=this.listHead;

        while (node!=null) {
            System.out.println(node.equation +" = " + node.result);
            node=node.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (this.listHead==null) {
            System.out.println("History Empty!");
            return;
        }

        EquationList node=this.listHead;

        this.listHead=node.next;

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.listHead.next=null;
        this.listHead=null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (this.listHead==null) {
            return 0;
        }
        int sum=0;
        EquationList node=this.listHead;

        while (node!=null) {
            sum=this.add(sum,node.result);
            node=node.next;
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
        if (this.listHead==null) {
            return 1;
        }
        int sum=1;
        EquationList node=this.listHead;

        while (node!=null) {
            sum=this.multiply(sum,node.result);
            node=node.next;
        }
        return sum;
    }
}