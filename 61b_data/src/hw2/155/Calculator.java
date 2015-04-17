import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equationhist; // Just declaring the existence 

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int nocarryadd= x^ y; 
        int carry= x & y; // Where there are both 1s, gotta carry
        int sum= 0;
        while (carry !=0) { 
            carry= carry << 1; // You're going to actually add the carry 1 step to the left
            sum= nocarryadd ^ carry; // Add it 
            carry= nocarryadd & carry; // Check if there's another carry
            nocarryadd= sum; 
        }
        return nocarryadd; // For some reason it doesn't work if you return sum
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int getBit(int x, int i) {
        int shift= x >>> i; 
        int result= shift & 1;
        return result; 
    }

    public int multiply(int x, int y) {
        // Gonna simulate human multiplication
        int i=0; 
        int partialsum= 0; 
        int newline= 0; 
        int ybit= 0; 
        while (i<=31) {// because ints in java are 32 bits
            ybit= getBit(y, i); // Starting from rightmost digit of y
            if (ybit ==0) {
                newline= 0; // Row of 0s
            }
            else {
                newline= x << i; // ybit= 1, so copy x and shift by i
            }
            partialsum= add(partialsum, newline); // Keep adding those lines of multiplication
            i= i+1;
        }
        return partialsum; 
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
        equationhist= new EquationList(equation, result, equationhist); // If pointer is pointing at different thing, then need new
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temppointer= equationhist; // Don't need new because pointing at same thing
        // Just going to change where temppointer is pointing to
        while (temppointer!=null) {
            System.out.println(temppointer.equation + " = " + temppointer.result); 
            temppointer= temppointer.next; 
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
        int i= 1; 
        EquationList temppointer2= equationhist; 
        while (i<=n) {
            System.out.println(temppointer2.equation + " = " + temppointer2.result); 
            temppointer2= temppointer2.next; 
            i= i+1; 
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationhist= equationhist.next; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (equationhist!= null) {
            equationhist= equationhist.next;
            // It's like undo for ALL THE WAY 
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/

    public int cumulativeSum() {
        int sum= 0; 
        // Borrowing part of the code from printallhistory
        EquationList temppointer= equationhist; // Don't need new because pointing at same thing
        // Just going to change where temppointer is pointing to
        while (temppointer!=null) {
            sum= sum + temppointer.result; 
            temppointer= temppointer.next; 
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
        int product= 1; 
        EquationList temppointer= equationhist;
        while (temppointer!=null) {
            product= product * temppointer.result;
            temppointer= temppointer.next; 
        }
        return product; 
    }
}