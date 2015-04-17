import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public int size=0;
    public EquationList hist = new EquationList("end",0,null); //sentinel node
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int result=0, //the ending result
            carry=0,  //1 if we carry to the next placeholder
            xbit, ybit, //the value of bit at the ith place
            ibit;  //the value of the ith bit of the result
        for (int i=0; i<32; i++) {
            xbit=(x & (1<<i))>>i;
            ybit=(y & (1<<i))>>i;

            if (xbit==0 & ybit==0 & carry==0) {
                ibit=0;
                carry=0;
            }
            else if (xbit==1 & ybit==1 & carry==1) {
                ibit=1;
                carry=1;
            }            
            else if ((xbit^ybit^carry)==1) {
                ibit=1;
                carry=0;
            }
            else {
                ibit=0;
                carry=1;
            }

            result=result | ibit<<i;
        }
        return result;
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
        /* this solution developed with help from pseudocode from Wikipedia: 
         * http://en.wikipedia.org/wiki/Bitwise_operation#Applications */
        int a = x, b = y;
        int result=0;
        Calculator calc=new Calculator();
        while (b!=0) {
            if ((b & 1) != 0) {
                result=calc.add(result,a);
            }
            a=a<<1;
            b=b>>1;
            if (b==-1) {
                break;
            }
        }    
        return result;
    }

/*
        int result=0,ybit;
        Calculator calc=new Calculator();
        for (int i=0; i<32;i++) {
            ybit=(y & (1<<i))>>i;
            if (ybit==1) {
                result=calc.add(result,(x<<i));
            }
        }
        return result;
    }
*/
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
        hist = new EquationList(equation, result, hist);
        size++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList ptr=hist;
        if (size==0) {
            return;
        }
        n++;
        while (n>0 && ptr.equation!="end") {
            System.out.println(ptr.equation + " = " + Integer.toString(ptr.result));
            ptr=ptr.next;
            n-=1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (size==0) {
            return;
        }
        else {
            hist=hist.next;
            size-=1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        hist = new EquationList("end",0,null);
        size=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int result = 0;
        EquationList ptr = hist;
        while (ptr.equation!="end") {
            result+=ptr.result;
            ptr=ptr.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int result = 1;
        EquationList ptr = hist;
        if (ptr.equation=="end") {
            result = 0;
            return result;
        }
        else {
            while (ptr.equation!="end") {
                result*=ptr.result;
                ptr=ptr.next;
            }
        }
        return result;
    }
}