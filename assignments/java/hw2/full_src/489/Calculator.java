import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDSS
    private EquationList eqList=null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        int z=0;
        int carry=0;
        int lastZ=0;
        
        for (int i=0; i<32; i++) {
            int lastX=x&1;
            int lastY=y&1;

            if (carry==1 & lastY==1 & lastX==1) {
                carry=1;
                lastZ=1;
            }

            else if (carry==1 & (lastY==1 ^ lastX==1)) {
                carry=1;
                lastZ=0;
            }

            else if (carry==1 & (lastX==0 & lastY==0)) {
                carry=0;
                lastZ=1;
            }

            else if (carry==0 & (lastY==1 ^ lastX==1)) {
                carry=0;
                lastZ=1;
            }

            else if (carry==0 & lastX==1 & lastY==1) {
                carry=1;
                lastZ=0;
            }

            else /*(carry=0 & lastY=0 & lastX=0)*/ {
                carry=0;
                lastZ=0;
            }

            z=((lastZ << i) | z);

            x=x>>1;
            y=y>>1;
        }
        return z;
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
        // YOUR CODE HERE
        int yAbs = absValue(y);
        int xAbs = absValue(x);

        int dummy=yAbs;
        int counter=0;
        int z=0;

        if (y == 1) {
            return x;
        }

        else if ((y == 0) | (x == 0)) {
            return 0;
        }

        while ((dummy >> 1) >= 1) {
            counter = this.add(counter,1);
            dummy = dummy >> 1;
        }

        // break y down into power of 2 component and remainder
        // i.e. 7=2^2+3*2^0
        int y2 = 1 << counter;
        int yRem = this.add(this.add(~y2,1),yAbs);

        z = z + xAbs << counter;
        z = z + multiply(xAbs,yRem);

        if ((x < 0) ^ (y < 0)) {
            return this.add(~z,1);
        } else {
            return z;
        }

        
    }

    private int absValue(int x) {
        if (x > 0) {
            return x;
        }
        else if (x < 0) {
            return this.add(~x,1);

        } else {
            return x;
        }
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
        // YOUR CODE HERE
        if (eqList == null) {
            eqList = new EquationList(equation, result, null);
        } else {
            EquationList pivot = eqList;
        while (pivot.next != null) {
            pivot = pivot.next;
        }
        pivot.next = new EquationList(equation, result, null);
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
        // YOUR CODE HERE
        EquationList pivot = eqList;
        if (eqList == null) {

        } else {
            while (pivot != null) {
                System.out.println(pivot.equation+" = "+pivot.result);
                pivot = pivot.next;
            }
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
        // YOUR CODE HERE
        EquationList pivot = eqList;
        
            for (int i=0; i<n; i++) {
                if (pivot != null) {
                    System.out.println(pivot.equation+" = "+pivot.result);
                    pivot = pivot.next;
                }
                
            }
         
            
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList pivot = eqList;
        if  (eqList == null) {
            
        } else if (eqList.next == null) {
            eqList = null;
        } else {
            while (pivot.next.next != null) {
            pivot = pivot.next;
        }
        pivot.next = null;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList pivot = eqList;
        int sum = 0;
        if (eqList == null) {
            return 0;
        } else {
            while (pivot != null) {
                sum = this.add(sum, pivot.result);
                pivot = pivot.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList pivot = eqList;
        int product = 1;
        if (eqList == null) {
            return 1;
        } else {
            while (pivot != null) {
                product = this.multiply(product, pivot.result);
                pivot = pivot.next;
            }
            return product;
        }
        
    }
}