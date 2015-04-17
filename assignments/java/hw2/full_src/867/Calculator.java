import list.EquationList;

public class Calculator {
//
    public EquationList eqList = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public static int add(int x, int y) {
        int answer = x ^ y;
        int answercarryover= (x & y);
        if ((answercarryover << 1) != 0) {
            return add (answer, (answercarryover << 1));
        }
        return answer;
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
   int product = 0;
   while (y != 0)
   {
    if ((y & 1) !=0)
        { 
            product = add(product, x);
        }
        
            x = x << 1;
          
          y = y >>> 1;
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
    
    public void saveEquation(String equation, int result) {
         if (eqList != null)
         { eqList = new EquationList(equation,result, eqList); }
         else {
            eqList= new EquationList (equation,result,null); }
       

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
        public void printAllHistory() {
            if (eqList == null)
               { System.out.print("");
       }
            while (eqList != null)
                { System.out.println(eqList.equation + " = " + eqList.result);
                  eqList = eqList.next;
                 }
             }


     

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int i = 0;
        EquationList pointy = eqList;
        while ((i < n) && (pointy != null))
            { System.out.println (pointy.equation + " = " + pointy.result) ;
            i = i + 1;
            pointy = pointy.next;}
         System.out.print("");

        } 

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
       if (eqList.next == null)
        return;
        else {
            eqList = eqList.next;
        }

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = null;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList pointy = eqList;
       while (pointy != null)
           { sum = sum + pointy.result;
            pointy = pointy. next;
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
       int product = 1;
       EquationList pointy = eqList;
       while (pointy != null)
        {product = product * pointy.result;
            pointy = pointy.next;
        }
        return product;
    }
}