import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList lst;
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
        int a = x^y;
        int b = x&y;
        if(b==0) return a;
        return add(a,(b << 1));

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
        if(x==1) return y;
        if(y==1) return x;
        int a = y;
        int b = x;
        int val = 1;

        if(x<0 && y>0 || (x>0 && y<0)){
            val = add(~val,1);
            if(y<0)
            {
                a = add(~a,1);
            }
            else
            {
                b = add(~b,1);
            }

        }
        else if(x<0 && y<0){
            a = add(~a,1);
            b = add(~b,1);
        }

        if(val>0)
            return add(a,multiply(a,add(b,add(~1,1))));
        else
            return add(~add(a,multiply(a,add(b,add(~1,1)))),1);
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
        if(lst == null)
            lst = new EquationList(equation,result,null);
        else
        {
            EquationList lt = lst;
            while(lt.next != null) lt = lt.next;
            lt.next = new EquationList(equation,result,null);
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
        EquationList lt = lst;
        int counter = 0;
        while(lt != null){
            counter ++;
            lt = lt.next;

        }
        for(int q=counter;q>0;q--)
            printHistory(q);
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
        EquationList lt = lst;
        int counter = 0;
        while(lt != null){
            counter ++;
            if(counter == n)
                System.out.println(lt.equation + " = " + lt.result);
            lt = lt.next;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList lt = lst;
        int counter = 0;
        if(lt.next == null) return;
        while(lt.next.next != null){
            lt = lt.next;
        }
        lt.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE

        lst = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList lt = lst;
        int counter = 0;
        while(lt != null){
            counter += lt.result;
            lt = lt.next;
        }
        return counter;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList lt = lst;
        int counter = 1;
        while(lt != null){
            counter *= lt.result;
            lt = lt.next;
        }
        return counter;
    }
}