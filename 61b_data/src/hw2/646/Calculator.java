import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList hist = new EquationList("",0,null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int temp; //created so ans and maskl can simultaneously re-define each other
        int ans = x ^ y; //initial flip (if nothing carries (maskl == 0), this is the answer)
        int maskl = (x & y) << 1; //initial carried ones
        /* while loop adds carried ones until none left.
        Because it is a loop, this considers carries that cause carries, etc. */
        while (maskl != 0) {
            temp = maskl;
            maskl = (ans & maskl) << 1;
            ans = ans ^ temp;
        }
        return ans;

        /* Ignore following, just me thinking it through visually:
x       00101100
y       10100111
actual  11010011
flipped 10001011
masked< 01001000
*/
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
        /* Initialize ans as 0 (conveniently provides answer for y=0,
        since in that case, while loop doesn't execute) */
        int ans = 0;
        /* Create int that is all zeros except for one 1. The lone one starts
        in the rightmost position, but moves right in each loop. 
        int power corresponds to position of one (ie one = 2^(power))*/
        int one = 1;
        int power = 0;
        while (y != 0) {
            //executes until all ones in y are 'used'
            if ((one & y) != 0) {
                //if y has a one in the current position
                ans = add(ans,x<<power); //add x*2^(power) to ans
                y = y ^ one; //remove used one, so the loop can eventually exit
            }
            // increase power and move lone one to the left (corresponds to next power of two)
            power++; 
            one = one<<1;
        }
        return ans;
/*Ignore this
x       0000 1100                        0000 0011
y       0000 0100, 0000 0110, 0000 0111, 1111 1111
ans     0011 0000, 0100 1000, 0101 0100, 1111 1101
*/
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
        hist.next = new EquationList(hist.equation,hist.result,hist.next);        
        hist.equation = equation;
        hist.result = result;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        //changed to be non-destructive
        EquationList temp = new EquationList(hist.equation,hist.result,hist.next);
        while (temp.next != null) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
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
        EquationList temp = new EquationList(hist.equation,hist.result,hist.next);
        while ((temp.next != null) && (n != 0)) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        hist = hist.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        hist = new EquationList("",0,null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = new EquationList(hist.equation,hist.result,hist.next);
        int sum = 0;
        while (temp.next != null) {
            sum = sum + temp.result;
            temp = temp.next;
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
        EquationList temp = new EquationList(hist.equation,hist.result,hist.next);
        int product = 1;
        while (temp.next != null) {
            product = product * temp.result;
            temp = temp.next;
        }
        return product;
    }
}