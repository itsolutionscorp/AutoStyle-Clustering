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
        int andand = x & y; //checks for overlaps of ones
        int sum = x ^ y; // addition of 0s and 1s
       // while (a != 0)

        while (andand!= 0) { //if andand != 0, then no overlaps
            int newand = andand << 1; //because there is an overlap, move to next place
            andand = newand & sum; //check if there is an overlap again to loop
            sum = newand ^ sum; //add the shifted overlap to the final answer, oror
        }
        return sum;
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
        int first = 0;
        int second = y;
        int sum = 0;
        int multiplier = 0;
        for (int i = 0; i < 32; i++) {
            second = y >> i;
            multiplier = second & 1;
            if (multiplier > 0) {
                first = x << i;
                sum = add(sum, first);
            }
            else {
                first = x << i;
            }
        }
        return sum;
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
    public EquationList lastmembervariable = null; //member variable to Calculator class
    public EquationList newmembervariable; //member variable to Calculator class
    
    public void saveEquation(String equation, int result) {
         EquationList createnewmembervariable = new EquationList(equation, result, lastmembervariable);
         lastmembervariable = createnewmembervariable;
         newmembervariable = createnewmembervariable;
        }
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() { ///////GOOD
        EquationList nextEquation = newmembervariable;
        while (nextEquation != null) {
            System.out.println(nextEquation.equation + " = " + nextEquation.result);
            nextEquation = nextEquation.next;
        }
    }
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) { ///////////GOOD
        // YOUR CODE HERE
        EquationList nextEquation = newmembervariable;
        while (n != 1) {
            nextEquation = nextEquation.next;
            n = n - 1;
        }
        System.out.println(nextEquation.equation + " = " + nextEquation.result);
    }    
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (newmembervariable == null) {
            System.out.println("Nothing to Undo");
        }
        else {
        newmembervariable = newmembervariable.next;
        lastmembervariable = lastmembervariable.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        newmembervariable = null;
        lastmembervariable = null;
    }





    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int total = 0;
        EquationList l = newmembervariable;

         while (l !=  null) {
            total = add(l.result, total);
            l = l.next;
         }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int total = 1;
        EquationList l = newmembervariable;

         while (l !=  null) {
            total = multiply(l.result, total);
            l = l.next;
         }
        return total;
    }
}




// package list;

// public class EquationList {
//     public EquationList next;
//     public String equation;
//     public int result;

//     /**
//      * Example of how EquationLists work: this list has two stored equations. 
//      * +-------------------+    +-------------------+
//      * | EquationList      |    | EquationList      |
//      * +-------------------+    +-------------------+
//      * | equation: "1 + 2" |    | equation: "3 * 4" |
//      * | result:   3       |    | result:   12      |
//      * | next:     --------+--->| next:     null    |
//      * +-------------------+    +-------------------+
//     **/

//     public EquationList(String equation, int result, EquationList next) {
//         this.equation = equation;
//         this.result = result;
//         this.next = next;
//     }
// }



