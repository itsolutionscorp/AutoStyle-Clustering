import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList data;
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
        // YOUR CODE HERE
        int m = 1;
        int carry = 0;
        int answer = 0;
        int mask = -2; // 111111110
        for (int a = 0; a<32; a++)
        {

            if ((x & m) != 0 && (y & m) != 0)
            {
                answer = answer & mask;
                carry = carry << a;
                answer = answer | carry;    
                carry = 1;
            }
            else if ((x & m) !=0 || (y & m) != 0)
            {
                if (carry == 0)
                {
                    mask = ~ mask;
                    answer = answer | mask;
                    mask = ~ mask; 
                }
                else
                {
                    answer = answer & mask;
                    carry = 1;
                }
            }
            else 
            {
                carry = carry << a;
                answer = answer | carry;
                carry = 0; 
            }
            mask = mask << 1;
            mask = mask | 1; 
            m = m << 1;
        }
        return answer;
    }

    /**
    * 100
    * 800
    * 100
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y)
    {
        int answer = 0;
        int temp = 0;
        int m = 1;
        for (int a=0; a<32; a++)
        {
            if ((y & m) != 0)
            {
                temp = x << a;
                answer = add(answer, temp);
            }
            
            m = m << 1;
        }
        // YOUR CODE HERE
        return answer;
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
    public void saveEquation(String equation, int result) 
    {
        //EquationList pointer = data;
        if (data == null)
        {
            data = new EquationList(equation, result, null);
        }
        else 
        {
            //while (pointer.next != null)
            //{
              //  pointer = pointer.next;
            //}
           // pointer.next = new EquationList(equation, result, null);
            data = new EquationList(equation, result, data);
        }
        // YOUR CODE HERE
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
        int counter = 1;
        EquationList pointer = data;
        while (pointer != null && pointer.next != null )
        {
            counter++;
            pointer = pointer.next;
        }
        printHistory(counter);
        // YOUR CODE HERE
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
        EquationList pointer = data;
        //for (int a = 0, a < n; a ++)
        int a = 0;
        while (pointer != null && a<n)
        {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
            a++; 
        }
        // YOUR CODE HERE

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() 
    {
        data = data.next;
        // YOUR CODE HERE
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() 
    {
        data = null; 
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
        int sum = 0;
        if (data == null)
        {
            return 0;
        }
        EquationList pointer = data;
        while (pointer != null)
        {
            sum = sum + pointer.result;
            pointer = pointer.next;
        }
        // YOUR CODE HERE
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() 
    {
        int product = 1;
        if (data == null)
        {
            return 0;
        }
        EquationList pointer = data;
        while (pointer != null)
        {
            product = product * pointer.result;
            pointer = pointer.next;
        }
        // YOUR CODE HERE
        return product;
    }
}