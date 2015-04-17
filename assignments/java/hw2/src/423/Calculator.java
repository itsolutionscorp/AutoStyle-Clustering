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
        int x1=0;
        int y1=0;
        while (x!=0) {
            x1=x;
            x=x&y;
            y=x1^y;
            x=x<<1;
        }
        return y;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    int counter=0;
    public int multiply(int x, int y) {
        int ptr=1;
         int y1=0;
         int x1=x;
         int x2=0;
         int y3=y & ptr;
        if (x==0|y==0) {
            return 0;
        }

        // y>0
        if (y>0) {
            if (y3==1) {
                y=add(y,-1);  
            }
            while (y>0) {
                y1= ptr&y;
                if (y1==1) {
                    x= helper(x);
                    x2=x2+x;
                    counter=counter+1;
                }
                if (y1==0) {
                    counter=counter+1;
                }
            y=y>>1;
            }
            counter=0;
            if (y3==1){
                return x2+x1;
            } else {
                return x2;
            }
        }

        //y<0
            counter=0;
             y=add(0,-y);
             y3=y&ptr;
             if (y3==1) {
                y=add(y,-1);  
            }
             while (y>0) {
                y1= ptr&y;
                if (y1==1) {
                    
                    x= helper(x);
                    x2=x2+x;
                    counter=counter+1;
                }
                if (y1==0) {
                    counter=counter+1;
                }
            y=y>>1;
            } if (y3==1){
                return -(x2+x1);
            }else {
                return -x2;
            }
    }
    public int helper(int x) {
        while (counter>0) {
            x= add(x,x);
            counter=counter-1;
        }
        return x;
    }
    
        /** int adder=x;
        if (y==0) {
            return 0;
        }
        if (y>0) {
            y=y-1;
            while (y>0) {
                x=add(x,adder);
                y=add(y,-1);
            }
        return x;
        }
        y=y+1;
        while (y<0) {
            x=add(x,adder);
            y=add(y,1);
            }
        return -x;
    } 
    **/

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public EquationList historyList;

    public void saveEquation(String equation, int result) {
        historyList=new EquationList(equation,result,historyList);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    EquationList currList=historyList;    
        while (currList!=null) {
            System.out.println(currList.equation+" = "+currList.result);
            currList=currList.next;
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
    EquationList currList=historyList;
        while (n>1) {
            currList=currList.next;
            n=n-1; 
            System.out.println(currList.equation+" = "+currList.result);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        historyList=historyList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (historyList!=null) {
            historyList= historyList.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList currList=historyList;
        int sumResult=0;
        while (currList!=null){
            sumResult=sumResult+currList.result;
            currList=currList.next;
        }
        return sumResult;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
       EquationList currList=historyList;
        int productResult=1;
        while (currList!=null){
            productResult=productResult*currList.result;
            currList=currList.next;
        }
        return productResult;
    }
}