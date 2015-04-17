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
   EquationList head;
    private static int getBit(int n, int i)
    {
        return n>>>i & 1;
    }
    private static int setBit(int n ,int i)
    {
        return  n | 1<<i;
    }
    
    public int add(int x, int y) {
        // YOUR CODE HERE
        int carry;
        while (y!=0)
        {
            carry=x&y;
            x=x^y;
            y=carry<<1;
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
        // YOUR CODE HERE
        if (x==0 || y==0)
        {
            return 0;
        }
        else if (x<0 && y<0)
        {
            return multiply(add(~x,1),add(~y,1));
        }
        else if (x<0)
        {
            x=add(~x,1);
            return add(~multiply(x,y),1);
        }
        else if (y < 0)
        {
            y=add(~y,1);
            return add(~multiply(x,y),1);
        }
        else
        {
            while (y!=1)
            {
                if ((y & 1)==0)
                {
                    x=x<<1;
                    y=y>>1;
                }
                else
                {
                    return add(x,multiply(x,add(y,add(~1,1))));
                    
                }

            }
        }
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
        
        head=new EquationList(equation,result,head);
        // YOUR CODE HERE
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
        printHistory(-1);
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
        EquationList e=head;
        while (n!=0 && e!=null)
        {
            System.out.println(e.equation+" = "+e.result);
            e=e.next;
            n-=1;
        }    
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
       head = head.next;// YOUR CODE HERE
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        head = null;// YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList e = head;
        int sum=0;
        while (e!=null)
            {
                sum = add(sum,e.result);
                e=e.next;
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
        // YOUR CODE HERE
        EquationList e = head;
        int product=1;
        while(e!=null)
        {
            product = multiply(product,e.result);
            e=e.next;
        }
        return product;
    }
}