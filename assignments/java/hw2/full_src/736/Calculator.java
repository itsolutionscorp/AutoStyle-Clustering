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

    public int add(int x, int y) 
    {
        if (y==0) 
        { 
            return x;
        } 
    
            return add(x ^ y, (x & y) << 1); 
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
    { int product = 0;
        while(x!=0)
            {
                if((x&1)!= 0) 
                {
                    product = add(product,y);
                } 
            x= x>>>1; 
            y= y<<1;
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
     */
     public EquationList eqnarray = null; 
     public EquationList pointer;  

    public void saveEquation(String equation, int result) 
    {  if(eqnarray == null)
        {
            eqnarray = new EquationList(equation, result, null); 
        }
           else
           {
            eqnarray = new EquationList (equation, result, eqnarray);   

           }
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
        if (eqnarray == null)
        {
            System.out.print(""); 
        }
        while (eqnarray != null) 
        {    
            System.out.println(eqnarray.equation +" "+ "=" +" "+ eqnarray.result); 
            eqnarray = eqnarray.next;  
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
    {  pointer = eqnarray; 
        if (eqnarray==null)
            {
                System.out.print(""); 
            }
        
        while((eqnarray != null) && (n>0))
        {  System.out.println(pointer.equation + " " + "=" + " " + pointer.result); 

        pointer = pointer.next; 
         n = n - 1;
        }
    }   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() 
    { 
        if(eqnarray == null)
        {
            return; 
        }
        else
        {
            eqnarray = eqnarray.next;
        }


    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() 
    {
       eqnarray = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() 
    { pointer = eqnarray;
        int sum = 0;  
      while(pointer!=null)
      {
        sum = sum + pointer.result;
        pointer = pointer.next; 
        }
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
        pointer = eqnarray; 
        int product =1;
        while(pointer != null)
        {
            product = product * pointer.result; 
            pointer = pointer.next; 
        }
        return product;  
       
    }
}