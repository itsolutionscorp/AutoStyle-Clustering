import list.EquationList;

public class Calculator 
{
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public EquationList x; 

    public int add(int x, int y) 
    {
        int number = 0; 
        int counter = 0; 
        int carry = 0;
        int max1 = 0; 
        while (counter <= 31)
        { 
        if ((getBit(carry, counter) ^ getBit(y,counter) ^ getBit(x,counter)) == 1)
            { 
                if (getBit(x, counter) == 1 && getBit(y,counter) == 1 && getBit(carry, counter) == 1)
                    { 
                        number = setBit(number, counter); 
                        counter = counter + 1;
                        carry = setBit(carry, counter);
                
                    }
                else
                    { 
                        number = setBit(number, counter); 
                        counter = counter + 1; 
                    }
            }
        else 
        {
                if ( (getBit(carry, counter) | getBit(y,counter) | getBit(x,counter)) == 1)
                {
                    counter = counter + 1;
                    carry = setBit(carry, counter);
                }
                else 
                    { 
                        counter = counter + 1;  
                    }
        } 
        
        }  
        return number;  

    }
    
    // returns the ith bit from the right of x   
    private int getBit(int x, int i)
    { 
        
        int count = x >> i;   
        if ((count ^ 1) < count) 
            return 1; 
        return 0; 

    }
    // returns a new number similar to x but with bit i set to 1 
    private int setBit(int x, int i)
    { 
        return x ^ (1 << i);
    }
    
    private int getLength(int x)
    { 
        if (x == 0)
            return 0; 
        return getLength(Math.abs(x >> 1)) + 1; 
    }
    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/ 
    // 
    public int multiply(int x, int y) 
    {
        // YOUR CODE HERE
        
        int total = 0; 
        int number = 0;
        int counter = 0; 
        while ( counter <= 31) 
            { 
                if (getBit(y, counter) == 1)
                { 
                    number = x; 
                    number = number << counter; 
                }
                else 
                { 
                    number = 0; 
                } 
                total = add(number, total); 
                counter = counter + 1; 
            }
        return total; 
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
        x = new EquationList(equation, result, x);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each e2quation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = x;
        int count = 1;

        while (temp != null) 
        {
            printHistory(count);
            count = count + 1;
            temp = temp.next;
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

        // YOUR CODE HERE
         
        EquationList hello  = x; 

        while( n > 1  )
        {
            hello = hello.next; 
            n = n - 1;
        } 
        System.out.println(hello.equation + " = " + hello.result);
         
    }   
     
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        x = x.next;
    }


    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {

        // YOUR CODE HERE
         while (x != null) 
         {
            undoEquation();
         }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() 
    {
        // YOUR CODE HERE
        int sum = 0;
        EquationList temper = x;

        while (temper != null) 
        {
            sum += temper.result;
            temper = temper.next;
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
        int product = 1;
        EquationList temper = x;

        while (temper != null) 
        {
            product *= temper.result;
            temper = temper.next;
        }
        return product;
    }
}