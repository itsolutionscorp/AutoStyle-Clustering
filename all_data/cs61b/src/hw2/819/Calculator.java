import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList list = null;


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int count = 0; 
        int cond = 31; 
        if (y < 0){
            cond = 32; 
        }
        while (count < cond){
            if((getBit(x, count) == 1) && (getBit(y, count) == 1)){
                x = carryBit(x, count);
            }
            else if(getBit(y, count) == 1){
                x = setBit(x, count);
            }
            count += 1; 
        }
        return x;
    }

    public int getBit(int b, int n){
        if (n == 31){
            return 1; 
        }
        else if(b > (b ^ (1 << n))){
            return 1;
        } 
        else{
            return 0;
        }
    }

    public int setBit(int b, int n){
        int num = 1 << n;
        return b | num;  
    }

    public int carryBit(int b, int n){
        if (n == 31){
            return b ^ (1 << 31);
        }
        else{
            int num = 1 << n;
            int k = n + 1; 
            if(getBit(b, k) == 1){
                b = b ^ num; 
                return carryBit(b, k);
            }
            else{
                return (b ^ num) | (num << 1);
            }
        }
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
        if((x < 0) && (y < 0)){
            return multiply(changeSign(x), changeSign(y));
        }
        else if(y < 0){
            return changeSign(multiply(x, changeSign(y)));
        }
        else if (x < 0){
            return changeSign(multiply(changeSign(x), y));
        }
        int count = 0;
        int total = 0;
        while (count < y){
            total = add(total, x);
            count += 1; 
        }
        return total; 
    }

    public int changeSign(int b){
        if (getBit(b, 0) == 1){
            return ~b | 1;
        }
        else{
            return carryBit(~b, 0);
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
        list = new EquationList(equation, result, list);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (list != null){
            System.out.println(list.equation + " = " + list.result);
            list = list.next; 
        }
        list = null;
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
        while (count < n){
            System.out.println(list.equation + " = " + list.result);
            list = list.next;
            count += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        list = list.next; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while(list != null){
            list = list.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList copy = list;
        int total = 0;
        while (copy != null){
            total += copy.result;
            copy = copy.next;
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
        EquationList copy = list; 
        int total = 1; 
        while (copy != null){
            total *= copy.result;
            copy = copy.next;
        }
        return total;
    }
}