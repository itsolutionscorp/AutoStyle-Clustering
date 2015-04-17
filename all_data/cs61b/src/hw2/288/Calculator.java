import list.EquationList;

public class Calculator {
    private EquationList listTotal;
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
        while((x&y)!=0 || (x^y)!=0) {
            if (x==0)
                return y;
            if (y==0)
                return x;
            int tempX = x;
            int tempY = y;
            x = tempX^tempY;
            y = (tempX&tempY) << 1;
        }
        if (x==0)
                return y;
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
        if (x==1)
            return y;
        if (y==1)
            return x;
        if(y==0 || x==0)
            return 0;

        if(isPowofTwo(y)){
            while (y>1 ) {
                int tempX = x;
                int tempY = y;
                x = tempX<< 1;
                y = tempY>> 1;
            
                //x = add(x, ((y>>1)));
                //x = add(x,y);
                //System.out.println("x " + x);
                //System.out.println("y " + y);
            }
            return x;

        }
        //System.out.println("got down with "+x + " "+y);
        int sum=0;

        for (int i=0;i<Math.abs(y);i++) {
            sum = add(sum,Math.abs(x));
        }
        //Neg case
        if (y<0 && x>0 ||(y>0 && x<0)) {
            return add(~sum,1);
        }
        return sum;
        
    }

    private boolean isPowofTwo(int x) {

        int base = 0;
        while (Math.pow(2,base) < 100000000) {
            if (Math.pow(2,base) == x)
                return true;
            base = add(base,1);

        }
        return false;
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
        if(listTotal==null) {
            listTotal = new EquationList(equation, result, null);
            //System.out.println("1");
        }
        else {
            EquationList temp = listTotal;
            listTotal = new EquationList(equation, result, temp);
            //System.out.println("2");
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
        if (listTotal == null) {
            //System.out.println("its null");
            return;
        }
        EquationList copyList = new EquationList(listTotal.equation,listTotal.result,listTotal.next);
        while (copyList!= null) {
            System.out.println(copyList.equation + " = " + copyList.result);
            copyList = copyList.next;
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
        if (listTotal == null) {
            //System.out.println("its null");
            return;
        }
        EquationList copyList = new EquationList(listTotal.equation,listTotal.result,listTotal.next);
        int i =0;
        while (copyList!=null && i<n) {
            System.out.println(copyList.equation + " = " + copyList.result);
            copyList = copyList.next;
            i++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        listTotal = listTotal.next;

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        listTotal = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum =0;
        EquationList copyList = new EquationList(listTotal.equation,listTotal.result,listTotal.next);
        while (copyList != null) {
            sum+= copyList.result;
            copyList = copyList.next;
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
        int product =1;
        EquationList copyList = new EquationList(listTotal.equation,listTotal.result,listTotal.next);
        while (copyList != null) {
            product*= copyList.result;
            copyList = copyList.next;
        }
        return product;
    }
}