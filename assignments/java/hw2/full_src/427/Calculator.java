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
    public int add(int x, int y) {
        int xory = x^y;
        int andy = x&y;
        andy = andy << 1;
        if ((xory & andy) == 0)
            return xory ^ andy;
        return add(xory, andy);
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
        int rev = 0;
        int result = 0;
        if (y==0 || x==0) return 0;
        if (x<0 && y<0) {x = add(~x,1); y = add(~y,1);}
        if (x<0 && y>0) {x = add(~x,1); rev = 1;}
        if (x>0 && y<0) {y = add(~y,1); rev = 1;}
        while (x!=0) 
        {   
            if ((y&1)!=0)
                result = add(result, x);
            x = x<<1;
            y = y>>1;
        }
        
        if (rev==1)
            return add(~result,1);
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

    EquationList myList = new EquationList("init", 0, null);
    public void saveEquation(String equation, int result) {
        EquationList tempList = new EquationList(equation, result, null);
        EquationList pointer = myList;
        

        if ((pointer.equation).equals("init"))
            myList = tempList;
    

        else
        {
            while (pointer.next != null)
                pointer = pointer.next;
            pointer.next = tempList;
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
        int len = 1;
        EquationList pointer = myList;
        while (pointer.next != null) {len++; pointer=pointer.next;}

        printHistory(len);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int len = 1;
        EquationList pointer = myList;
        while (pointer.next != null) {len++; pointer=pointer.next;}


        for (int i=0; i<n; i++)
        {   
            pointer = myList;
            for (int j=i; j<len-1; j++)
                pointer = pointer.next;
            if (i>len-1) break;
            if (!(pointer.equation).equals("init"))
                System.out.println(pointer.equation + " = " + pointer.result);
            
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList pointer = myList;

        if (pointer.next == null)
            myList = new EquationList("init", 0, null);
        else
        {
            while (pointer.next.next!=null)
                pointer = pointer.next;
            pointer.next = null;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        myList = new EquationList("init", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = myList;
        int temp = 0;

        while (pointer != null)
        {
            temp += pointer.result;
            pointer = pointer.next;
        }
        return temp;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList pointer = myList;
        int temp = 1;
        
        while(pointer!=null)
        {
            temp = temp * pointer.result;
            pointer = pointer.next;

        }
        return temp;
    }
}