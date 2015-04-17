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
    private EquationList history;

    public int add(int x, int y) {
        int k = 0;
        boolean carry = false;
        int sum = 0;
        while (k < 32){
            int xBit = getBit(x, k);
            int yBit = getBit(y, k);
            if (xBit == yBit){
                //if the xBit and yBit are both 0 or both 1
                if (xBit == 0){
                    if (carry == true){
                        sum = setBit(sum, k);
                        carry = false;
                    }
                }
                else{
                    if (carry == true){
                        sum = setBit(sum, k);
                        //carry still set to true
                    }
                    carry = true;  
                }
            }
            else{ //then either xBit or yBit is 1
                if (carry == false){
                    sum = setBit(sum, k);
                } 
            }
            k += 1;
        }
        return sum;
    }

    private int getBit(int x, int i){
        int k = x >>> i;
        return (k & 1);
    }

    private int setBit(int x, int i){
        int k = 1 << i;
        return (x | k);
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

        int product = x;
        int k = 2;

        if ((y < 0) && (x > 0)){
            return multiply(y, x);
        }

        if ((x < 0) && (y < 0)){
            return multiply(absValue(x), absValue(y));
        }

        if (y == 0){
            return 0;
        }
        if (y == 1){
            return product;
        }

        while (k < y){
            product = product << 1;
            k = k << 1;
        }

        if (k == y){
            return (product << 1);
        }

        k = k >> 1;

        int n = k ^ y;

        while (n > 0){
            product = add(product, x);
            n = add(n, -1);
        }

        return product;

    }

    private int absValue(int x){
        int value = ~x;
        value = add(value, 1);

        return value;
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

        EquationList newEq = new EquationList(equation, result, history);
        history = newEq;

        // EquationList tail = history;

        // if (history.equation == null){
        //     history.equation = equation;
        //     history.result = result;
        // }
        // else{
        //     while (tail.next != null){
        //         tail = tail.next;
        //     }
        //     tail.next = new EquationList(equation, result, null);
        // }
        
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

        EquationList temp = history;
        while (temp.next != null){
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
        }

        if (temp.equation != null) {
            System.out.println(temp.equation + " = " + temp.result);
        }

        temp = history;

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



        EquationList temp = history;

        int i = 0;

        while (temp != null){
            temp = temp.next;
            i += 1;
        }

        temp = history;

        i = i - n;

        while (i > 1){
            temp = temp.next;
            i -= 1;
        }

        if (temp.equation != null){
            System.out.println(temp.equation + " = " + temp.result);

        }

        

        temp = history;


    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {

        int sum = 0;

        EquationList temp = history;
        if (temp.next == null){
            return temp.result;
        }
        else{
            while (temp != null){
                sum += temp.result;
                temp = temp.next;
            }
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
        // YOUR CODE HERE
        int product = 1;

        EquationList temp = history;
        if (temp.next == null){
            return temp.result;
        }
        else{
            while (temp != null){
                product *= temp.result;
                temp = temp.next;
            }
        }
        return product;
    }
}