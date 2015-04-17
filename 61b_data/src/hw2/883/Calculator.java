import list.EquationList;


public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELD
    EquationList xiaowu=null;
    int shentu=0;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int b=0;
        while((b=(x & y))!=0){
            x = (x ^ y);
        y = (b<<1);
        }

        return (x^y);
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int two(int x)
    {
       return add((~x),1);
    }

    public int mult(int x, int y)
    {
        int a =1073741824;
        int b = y;
        int c =x;
        while(((a>>=1)!=0)&&((b&c)!=0));
        while ((y>>=1)!=0){
        c<<=1;
        if ((a&y)!=0) 
            c= add(c, x);

        }
        return c;
    }

    public int multiply(int x, int y) {
        
        if (x>=0 && y>=0)
return mult(x, y);
else
if(x<0){if(y<0) return mult(x, y); else return two(mult(two(x), y));}
else
if(y<0)
return two(mult(x, two(y))); 
return 0;
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
        xiaowu = new EquationList(equation, result, xiaowu);
        shentu=shentu+1;
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
        printHistory(shentu);
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
        EquationList mingjian=xiaowu;
        for (int i=1; i<=n; i++) {
            System.out.println(mingjian.equation);
            System.out.println(mingjian.result);
               mingjian=mingjian.next;
    }    
}

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        xiaowu=xiaowu.next;
        shentu-=1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        xiaowu=null;
        shentu=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int a=0;
        EquationList mingjian=xiaowu;
        for (int i=1; i<=shentu; i++) {
            a=add(mingjian.result, a);
               mingjian=mingjian.next;
    }    
        return a;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int a=1;
        EquationList mingjian=xiaowu;
        for (int i=1; i<=shentu; i++) {
            a=multiply(mingjian.result, a);
               mingjian=mingjian.next;
    }    
        return a;
    }
}