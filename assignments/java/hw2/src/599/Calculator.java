//Aravind Sivakumar
//25226962
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // MY CODE HERE
        int carrySpots;      //this represents all the positions that will require a carry (aka if each addend has a "1" in that spot
        int sumOfSpots;      // this represents the sum of each pair of bits, disregarding carries

        // we loop until all the carry spots have been taken care of.  And if 0 starts as 0, just automatically
        while (x != 0) {
            carrySpots = x & y;
            sumOfSpots = x ^ y;

            x = carrySpots << 1;    /* We shift the carrySpots one slot to the left, so all the "1s" get carried appropriately, one spot left */
            y = sumOfSpots;
        }
        // once all the carry spots have been taken care of and carrySpots is 0, y is good to go
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
    public int multiply(int x, int y) {
        // MY CODE HERE
        int result = 0;
        //ystem.out.print(result); System.out.print(", "); System.out.print(x); System.out.print(", "); System.out.print(y); System.out.println(); 
        while (x != 0) {
            // if the rightmost bit is a 1, then increment the result by whatever y is at the time
            if ((x & 1) != 0) {
                //result = this.add(result, y);
                result = add(result, y);
            }
            /* logical shift x to the right (>>>) so that what was previously the penultimate bit is now the last bit
            shift y to the left (<<) to double it, so that the thing that gets added to the result is multiplied by 2 the correct number of times, according to which bit of x we're inspecting
            ex. if we're inspecting the third bit (from right) of x, y should equal 4 times its initial value.
            So if the third-from-right x-bit is a 1, we increment the product by 4y, to account for the fact that x has a 1 in the "4s place" */
            x = x >>> 1;
            y = y << 1;
        //System.out.print(result); System.out.print(", "); System.out.print(x); System.out.print(", "); System.out.print(y); System.out.println(); 
        }
        return result;

        /*System.out.println(x);
        System.out.println(y);
        while (y != 1) {
            x = x << 1;
            System.out.println(x);
            y = y >>> 1;
            System.out.println(y);
        }
        return x; */
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
        // MY CODE HERE
        
        EquationList thisEquation = new EquationList(equation, result, null);

        // if this is the first equation in the history, we must create a new Equationlist with only this equation        
        if (history == null) {
            history = thisEquation;
        }
        // otherwise, we use a temp variable called cycler to cycle through the history, and add this equation to the end of the existing history
        else {
            EquationList cycler = history;
            while (cycler.next != null) {
                cycler = cycler.next;
            }
            cycler.next = thisEquation;
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
        // MY CODE HERE
        int length = this.getHistoryLength();
        this.printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public int getHistoryLength() {
        if (history == null) {
            return 0;
        }
        EquationList cycler = history;
        int length = 0;
        while (cycler != null) {
            length += 1;
            cycler = cycler.next;
        }
        return length;
    }

    public void printHistory(int n) {   
        // MY CODE HERE
        
        // base case
        if (n == 0 || history == null) {
            return;
        }
        // the determine the number of steps we must make
        // we make that many steps, then print out the appropriate equation
        // then we increment counter, which in turn decrements the number of turns we must make on the next loop
        int counter = 0;
        EquationList cycler = history;
        while (counter < n) {
            int numSteps = this.getHistoryLength() - counter;
            int steps = 1;
            while (steps < numSteps) {
                cycler = cycler.next;
                steps += 1;
            }
            System.out.print(cycler.equation); System.out.print(" = "); System.out.println(cycler.result);
            cycler = history;
            counter += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // MY CODE HERE
        if (this.getHistoryLength() == 0 || this.getHistoryLength() == 1) {
            history = null;
            return;
        }
        EquationList cycler = history;
        while (cycler.next.next != null) {
            cycler = cycler.next;
        }
        cycler.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // MY CODE HERE
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // MY CODE HERE
        EquationList cycler = history;
        int sum = 0;
        while (cycler != null) {
            sum += cycler.result;
            cycler = cycler.next;
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
        // MY CODE HERE
        EquationList cycler = history;
        int product = 1;
        while (cycler != null) {
            product = product * cycler.result;
            cycler = cycler.next;
        }
        return product;
    }
}