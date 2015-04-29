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

    /** returns the ith bit from the right of x
     * getBit(20,0) -> returns the rightmost bit, which is a 0
     * getBit(20, 2) -> returns 2nd to right bit, which is a 1
     **/
    private int getBit(int x, int i)
    {
        return (x >> i) & 1;
    }

    /** returns a new number equal to x but with bit i set to 1
     * setbit(20, 3) -> 28
     **/


    public int add(int x, int y)
    {
        int sum=0, carry=0;
        for (int i = 0; i < 32; i++) // one standard operator used
        {
            int xi = (x & (1 << i));
            int yi = (y & (1 << i));
            sum |= (xi^yi) ^ carry;
            carry = ((xi&yi) ^ (carry & (xi^yi))) << 1;
        }

        return sum;

    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y)
    {
        int sum=0;
        for(int i = 0; i < 32; i++)
        {
            if((y & (1 << i)) != 0)
                sum = add(x, sum);
            x <<= 1;
        }
        return sum;

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

    EquationList list = new EquationList(null, 0, null);

    public void saveEquation(String equation, int result)
    {
        EquationList tempList = new EquationList(equation, result, null);

        list = new EquationList(equation, result, list);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory()
    {
        EquationList tempList = list;
        printHelper(tempList);
    }

    public void printHelper(EquationList plist)
    {
        while(plist.equation != null)
        {
            System.out.print(plist.equation);
            System.out.print(" = ");
            System.out.println(plist.result);
            plist = plist.next;
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n)
    {
        EquationList tempList = list;
        printHelper(tempList, n);
    }

    public void printHelper(EquationList plist, int n)
    {
        while((plist.equation != null) && (n > 0))
        {
            System.out.print(plist.equation);
            System.out.print(" = ");
            System.out.println(plist.result);
            plist = plist.next;
            n--;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation()
    {
        list = list.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory()
    {
        list = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum()
    {
        return sumHelper(list, 0);
    }

    public int sumHelper(EquationList list, int sum)
    {
        if (list.next == null)
            return (sum + list.result);
        return sumHelper(list.next, (sum + list.result));
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct()
    {
        return prodHelper(list, 1);
    }

    public int prodHelper(EquationList list, int prod)
    {
        if (list.next == null)
            return prod;
        return prodHelper(list.next, (prod * list.result));
    }
}