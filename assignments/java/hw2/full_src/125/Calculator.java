import list.EquationList;

public class Calculator {
    public EquationList eqList = null ;
    public int length = 0;
    public EquationList revEqList = null ;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (x == 0)
        {
            return y ;
        }

        if (y == 0)
        {
            return x ;
        }
        return add(x ^ y, (x & y) << 1) ;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/

    public int negate(int x)
    {
       /* if ( x == 0)
        {
            return x ;
        }*/
        // System.out.println("X = "+x+" ; -X = "+(this.add(~x, 1)));
        return this.add(~x, 1);
    }

    public int multiply(int x, int y) {
        if (x == 0 || y == 0)
        {
            return 0 ;
        }
        
        if (x < 0 && y < 0)
        {
            return this.multiply(this.negate(x), this.negate(y));
        }

        if (x < 0)
        {
            return this.negate(this.multiply(this.negate(x), y));
        }

        if (y < 0)
        {
            return this.multiply(y, x);
        }

        int res = 0 , i = 0, one = 1;
        while (y != 0)
        {
            // System.out.println("X = "+x+" ; Y = "+y);
            if ((y & one) == one)
            {
                /*System.out.println(y);*/
                res = this.add(res, x << i);
            }
            i += 1;
            y = y >> 1;
        }
        return res ;
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
        if (this.eqList == null)
        {
            this.eqList = new EquationList(equation, result, null);
        }
        else
        {
            EquationList ptr = this.eqList;
            while (ptr.next != null)
            {
                ptr = ptr.next ;
            }
            ptr.next = new EquationList(equation, result, null);
        }
        
        this.length += 1;
        
        if (this.revEqList == null)
        {
            this.revEqList = new EquationList(equation, result, null);
        }
        else
        {
            EquationList ptr = this.revEqList;
            this.revEqList = new EquationList(equation, result, ptr);
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
        this.printHistory(this.length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printtHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        /*if (n < 0)
        {
            System.out.println("Negative number entered");
        }

        else if (n == 0)
        {
            return ;
        }

        else if (n > this.length)
        {
            System.out.println("n exceeds how many are stored in the history");
        }*/

        if ((n < 0) || (n > this.length))
        {
            ;
        }

        else
        {
            EquationList ptr = this.revEqList;
            while (n > 0)
            {
                System.out.println(ptr.equation + " = " + ptr.result);
                ptr = ptr.next;
                n -= 1;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (this.eqList == null || this.revEqList == null)
        {
            ;
        }

        else if (this.eqList.next == null || this.revEqList.next == null)
        {
            this.clearHistory();
        }

        else
        {
            EquationList ptr = this.eqList;
            while (ptr.next.next != null)
            {
                ptr = ptr.next;
            }
            ptr.next = null ;
            
            this.length -= 1;

            EquationList rptr = this.revEqList;
            this.revEqList = rptr.next ;
        }


    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.eqList = null ;
        this.length = 0;
        this.revEqList = null ;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList ptr = this.eqList;
        int sum = 0;
        while(ptr != null)
        {
            sum += ptr.result;
            ptr = ptr.next;
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
        EquationList ptr = this.eqList;
        int product = 1;
        while(ptr != null)
        {
            product *= ptr.result;
            ptr = ptr.next;
        }
        return product;
    }
}