import list.EquationList;

public class Calculator {

    
    public EquationList eqlst = null;
    public EquationList lasteq = null;
    public int historyLength = 0;



    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {


        //Disclaimer: This is a modified version of a strategy I found StackOverflow. I personally wouldn't have 
        //approached it this way right away, but the solution makes sense to me. 
        int xandy = x & y;
        int xory = x ^ y;
        int temp;
        while (xandy !=0) {
            
            xandy = xandy << 1;//shift xandy by 1
            temp = xandy ^ xory; //save the nonoverlapping bits in xandy and xory
            xandy = xandy & xory; //replace xandy with the overlapping bits between xandy and xory 
            xory = temp; //replace xory with the saved nonoverlapping bits in temp
        }

        return xory;


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


        //Disclaimer: This is a modified version of a strategy I found StackOverflow. I personally wouldn't have 
        //approached it this way right away, but the solution makes sense to me.
        int result = 0;
        while (x!= 0){
            if ((y & 01) != 0){
                result = result + x;
            }
            x <<= 1;
            y >>= 1;
    }
            return result;


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


        if (eqlst == null) {
            eqlst = new EquationList(equation, result, null);
        } else {
            EquationList pointer = eqlst;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = new EquationList(equation, result, null);
            //System.out.print(historyLength);
        }
        historyLength += 1;


    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {

            printHistory(historyLength);
                }



    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {


        if (n == 0 || eqlst == null) {
            return;
        }
        EquationList temp = eqlst;
        for(int i = 0; i<n; i++) {
            if (temp != null) {
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;           
            }
        }
        return; 
    }  



    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {


        if (eqlst == null) {
            return;
        } else if (eqlst.next == null) {
            eqlst = null;
        } else {
            EquationList temp = new EquationList(eqlst.equation, eqlst.result, eqlst.next);
            EquationList temppointer = temp;
            EquationList pointer = eqlst.next;
            while (pointer.next != null) {
                temp.next = new EquationList(pointer.equation, eqlst.next.result, eqlst.next.next);
                pointer = pointer.next;
                temp=temp.next;
                }
            historyLength -= 1;
            eqlst = temppointer;
        }

    }




    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {


        eqlst = null;


    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {


        int sumz = 0;
        if (eqlst == null) {
            return 0;
        } else {
            EquationList temp = eqlst;
        for(int i = 0; i < historyLength; i++) {
            sumz = add(sumz,temp.result);
            temp = temp.next;
        }
    }
        return sumz;


    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE


        int prod = 0;
        if (eqlst == null) {
            return 0;
        } else {
        EquationList temp = eqlst;
        for(int i = 0; i < historyLength; i++) {
            prod = multiply(prod,temp.result);
            temp = temp.next;
        }
    }
        return prod;


    }
}