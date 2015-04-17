import list.EquationList;

public class Calculator {
    public EquationList hist = null;

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
    //     int carry = 0;
    //     boolean[] ansx = new boolean[32];
    //     boolean[] ansy = new boolean[32];
    //     boolean[] answer = new boolean[32];
    //     int sum = 0;
    //     for (int i=31; i>=0; i-=1){
    //         ansx[i] = mask & x;
    //         ansy[i] = mask & y;
    //         mask = mask << 1;
    //     }
    //     for (int i=31; i>=0; i-=1){
    //         boolean result = ansx[i] ^ ansy[i] ^ carry;
    //         answer[i] = result;
    //         carry = ansx[i] & ansy[i];
    //     }
    //     for (int i=31; i>=0; i-=1){
    //         sum = ((int)(answer[i]) << (31-i)) ^ sum;
    //     }
    // }

        int ansx = x;
        int ansy = y;
        int result;
        result = ansx ^ ansy;
        int mask = 1;
        int c = 0;
        int k;

        for (int i=1; i<32; i+=1){
          
            c = ansx & ansy & mask | ansx & c & mask | ansy & c & mask;
            k = c <<i;
            result = result ^ k;
            ansx >>= 1;
            ansy >>= 1;
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
        int product = 0;
        int ansx = x;
        int ansy = y;
// 3*4, 6*2, 12*1
// flip bits and add one (for neg num)
        if (ansx < 0) {
            ansx = add(1, ~ansx);
            }

        if (ansy <0){
            ansy = add(1, ~ansy);
            } 

        // if ((ansx < 0) && (ansy < 0)) {
        //     ansx ~= ansx;
        //     ansx = add(1, ansx);
        //     ansy ~= ansy;
        //     ansy = add(1, ansy);
        //     }
                
        while (ansy != 0) {
            if ((ansy & 1) == 1){
                product = add(product, ansx);
            }
            ansy >>= 1;
            ansx <<= 1;
            
        }

// Check if the original numbers were negative so that product is negative if needed
        if (x < 0) {
            product = add(1, ~product);
        }

        if (y <0){
            product = add(1, ~product);
        }

        return product;

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
        hist = new EquationList(equation, result, hist);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(2147483647);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int count = 0;
        EquationList currhist = hist;
        while ((count < n) && (currhist != null)){
            System.out.println(currhist.equation + " = " + String.valueOf(currhist.result));
            currhist = currhist.next;
            count += 1;
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
        hist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList now = hist;
        int sum = 0;
        while (now !=null){
            sum = add(sum, now.result);
            now = now.next;
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
        EquationList now = hist;
        int productc = 1;
        while (now != null){
            productc = multiply(productc, now.result);
            now = now.next;
        }
        return productc;

    }
}