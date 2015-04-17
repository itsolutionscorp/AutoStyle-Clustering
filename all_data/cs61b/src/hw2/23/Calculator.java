import list.EquationList;

public class Calculator {
    EquationList storage = null;
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
        int result = x ^ y;//If there was no overflow.
        int handler = x & y;//Checks to see if overflow.
        int place_holder = result;//Needed to carry the 1 and save result.
        for (int m = 0;m<32;m++) {//32 bits for ints
            if (handler != 0) {//Checks to see if no overflow.
                handler = handler << 1;//Carries 1 (Multiplies by 2)
                place_holder = result ^ handler;//Holding current result (If handler is done).
                handler = handler & result;//Checking for overflow.
                result = place_holder;//Current result
            }
            else {
                break;//No overflow---> break out of loop
            }
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
        /*In this part, we will use x as the counter to count how 
        * many times to add y.
        */
        int place_holder = 0;//Keeps the result updated.
        while (x != 0) {//Checks to see if counter is empty.
        /*Checks to see if, for a given place, there is a 1 or a 0.
        * If 0, there is nothing to add.
        */
            if ((x & 1) == 1) {
                place_holder = add(place_holder,y);//Adding y
            }
            /*These keep the places as in (1's place, 2's place, 4's place)
            * of x and y synchronized. As we check in higher places of x for 
            * 1's and 0's, we are adding the appropriate y for that magnitude.
            */
            x = x >>> 1;
            y = y << 1;
        }
        return place_holder;
        /*Below is my original implementation but it is probably slow.
        * Also, it doesn't use bit operations is a meaningful way.
        * Thus, I was forced to do the essentially identical process 
        * as seen above. In both, x is simply a counter for adding y.
        */
        // if (x==0) {
        //     return 0;
        // }
        // else if (x<0) {
        //     return add(-y,multiply(add(x,1),y));
        // }
        // else {
        //     return add(y,multiply(add(x,-1),y));
        // }
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
        storage = new EquationList(equation,result,storage);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList helper = storage;
        while (helper != null) {
            System.out.println(helper.equation + " = " + helper.result);
            helper = helper.next;
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
        EquationList helper = storage;
        while (helper != null && n>0) {
            System.out.println(helper.equation + " = " + helper.result);
            helper = helper.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        storage = storage.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        storage = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList helper = storage;
        int result = 0;
        while (helper != null) {
            result += helper.result;
            helper = helper.next;
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
        EquationList helper = storage;
        int result = 1;
        while (helper != null) {
            result *= helper.result;
            helper = helper.next;
        }
        return result;
    }
}