import list.EquationList;

public class Calculator 
{
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList aList;
    //public EquationList newList;
    public int size;
    public String a;
    public int b;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) 
    {
        int z = 0;
        while (y != 0)
        {
            z = x & y;
            x = x ^ y;
            y = z << 1;
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
    public int multiply(int x, int y) 
    {
        // YOUR CODE HERE
        //Trivial Method of Multiplication
        /*int n = 0;
        int temp = x;
        while (y != 1)
        {
            n = add(x, temp);
            x = n;
            y = y - 1;
        }
        return n;*/
        int product = 0;
        while (x != 0)
        {
            if ((y & 1) == 1)
            {
                product = add(product, x);         
            }
            y = y >> 1;
            x = x << 1;

        }
        return product;
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
    //Adapted from Professor Hug's Lecture Code "sList" on 2/2/2015
    public void saveEquation(String equation, int result) 
    {
        //Insert in the Front
        aList = new EquationList(equation, result, aList);
        size = size + 1;
        //Insert in the Back
        // if (aList == null)
        // {
        //     aList = new EquationList(equation, result, null);
        //     size = 1;
        //     return;
        // }
        // EquationList bList = aList;
        // while (bList.next != null)
        // {
        //     bList = bList.next;
        // }
        // bList.next = new EquationList (equation, result, null);
        // size = size + 1;
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
        EquationList tempoList = aList;
        while (tempoList != null)
        {
            if(tempoList.equation != null)
            {
                System.out.println (tempoList.equation + " = " + tempoList.result);
            }
            tempoList = tempoList.next;
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
        EquationList tempList = aList;
        while (n != 0)
        {
            if (tempList.equation != null)
            {
                System.out.println(tempList.equation + " = " + tempList.result);
            }
            tempList = tempList.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() 
    {
        aList = aList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() 
    {
        aList = new EquationList(a, b, null);
        size = 0;
        // YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() 
    {
        int totalSum = 0;
        EquationList sumList = aList;
        if(sumList == null)
        {
            return 0;
        }
        while (sumList != null)
        {
            if(sumList.equation != null)
            {
                totalSum = add(totalSum, sumList.result);
            }
            sumList = sumList.next;
        }
        return totalSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() 
    {
        int totalProduct = 1;
        EquationList productList = aList;
        if(productList == null)
        {
            return 1;
        }
        while (productList != null)
        {
            if(productList.equation != null)
            {
                totalProduct = multiply(totalProduct, productList.result);
            }
            productList = productList.next;
        }
        return totalProduct;
    }
}