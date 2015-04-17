import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList hxstory;
    public EquationList copied;
    public int lengthz;

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

        int carriedNum = 0;
        int sum = 0;
        int i = 0;

        // need to have x move over

        while (i < 32){ // 32 bits in a number...

            if (carriedNum == 0){
                if ( (x & (1 << i)) >= 1) { //is this how u shift?
                
                    if ( (y & (1 << i)) >= 1) { // 1, 1
                        // sum = sum | (carriedNum << i);
                        carriedNum = 1;
                    }
                    else if ( ( y & (1 <<i)) == 0){
                        sum = sum | (1 << i);
                    }           
                }

                else if ( (x & (1 << i)) == 0) {
                    if ( (y & (1 << i)) >= 1) { // 1, 1
                        sum = sum | (1 << i);
                    }
                }
            }

            else if (carriedNum == 1){
                if ( (x & (1 << i)) >= 1) { //is this how u shift?
                
                    if ( (y & (1 << i)) >= 1) { // 1, 1
                        // sum = sum | (carriedNum << i);
                        sum = sum | (1 << i);
                        carriedNum = 1;
                    }
                    else if ( ( y & (1 <<i)) == 0){
                        carriedNum = 1;
                    }           
                }

                else if ( (x & (1 << i)) == 0) {
                    if ( (y & (1 << i)) >= 1) { 
                        carriedNum = 1;
                    }
                    else if ( (y & (1 << i)) >= 0){
                        sum = sum | (1 << i);
                        carriedNum = 0;                        
                    }
                }
            }
        i += 1;
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
        // YOUR CODE HERE --> utilizing al khwarizimi's 2nd alg.
        int product = 0;
        int second = y;
        int first = x;

        while (second != 0){
            if ((second & 1) != 0){
                product += first;
            }
            first <<= 1;
            second >>>= 1;
        }
            return product;
    }
        // int first = x;
        // int second = y;

        // int product = 0;
        

        // while (second != 0){
        //     if (first > 0 && second > 0 || first < 0 && second < 0) {
        //         if ( (second & 1) == 1) { //checking last bit value
        //             product = add(product, first);
        //     }

        //         else if ( (second & 1) == 0 ){
        //         //product stays the same (multiplying by 0)
        //             product = product;
        //     }
        // }
        //     else if (first > 0 && second < 0 || first < 0 && second > 0) {
        //         if ( (second & 1) == 1) { //checking last bit value
        //             first = ~first;
        //             product = add(product, first);
        //     }

        //         else if ( (second & 1) == 0 ){
        //         //product stays the same (multiplying by 0)
        //             product = product;
        //     }
        // }

        //     first <<= 1; // accounting for the "bigger" place, multiplying by 10 
        //     second >>= 1; //to get to the next digit (individual digits)
        // }
        // return product;
        //    }

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
        hxstory = new EquationList(equation, result, hxstory);
        lengthz += 1;
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
        printHistory(lengthz);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // for (int i = 0; i < lengthz; i += 1){
        //     System.out.println(hxstory.equation + " = " + hxstory.result);
        // }
        copied = hxstory;

        while (copied != null && n != 0) {
            System.out.println(copied.equation + " = " + copied.result);
            copied = copied.next;
            n -= 1; 
        }

        copied = hxstory;
        // if (n == 0) {
        //     System.out.println(hxstory.equation + " = " + hxstory.result);
        // }
        // else { //make sure that you have enough hxstory
        //         printHistory(n-1); //DON'T FORGET TO DECREMENT
        //     } 
        // }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (hxstory != null) {
            hxstory = hxstory.next;
        }

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        hxstory = null;
        // while (lengthz > 0){
        //     if (hxstory == null) {
        //         lengthz = 0;
        // }
        //     else {
        //         hxstory = hxstory.next;
        //         lengthz--;
        // }
        // }
    }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        copied = hxstory;

        while (copied != null) {
            sum = add(sum, copied.result);
            copied = copied.next;
        }
        copied = hxstory;
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int product = 1;

        copied = hxstory;

        while (copied != null) {
            product = multiply(product, copied.result);
            copied = copied.next;
        }
        copied = hxstory;
        return product;
    }
}