import list.EquationList;

public class Calculator {
    EquationList history;
    // YOU MAY WISH TO ADD SOME FIELDS
    // public static void main(String[] args){
    //     int a = 15;
    //     System.out.println(Integer.toBinaryString(a));
    //     a = a >>> 1;
    //     System.out.println(Integer.toBinaryString(a));
    // }
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public static int add(int x, int y) {
        int carry = 0;
        while(x != 0){
            // System.out.println(Integer.toBinaryString(carry));
            // System.out.println(Integer.toBinaryString(x));
            // System.out.println(Integer.toBinaryString(y));
            carry = y & x;
            carry  = carry << 1;
            y = y ^ x;
            x = carry;
        }
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
        int original = x;
        x = 0;
        System.out.println(original);
        if(original == 0 || y == 0){
            return 0;
        }  else if(y > 0) {
            int i = 0;
            while(i<y){
                x = this.add(x, original);
                i += 1;
            }
            return x;
        } else {
            y = ~y;
            y = this.add(y, 1);
            int i = 0;
            while(i<y){
                x = this.add(x, original);
                i += 1;
            }
            x = ~x;
            x = this.add(x, 1);
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
        EquationList temp = this.history;
        history = new EquationList(equation, result, temp);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList pointer = this.history;
        while(pointer != null){
            System.out.print(pointer.equation + " = ");
            System.out.print(pointer.result + "\n");
            pointer = pointer.next;
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
        int counter = 0;
        EquationList pointer = this.history;
        while(pointer != null && counter < n){
            System.out.print(pointer.equation + " = ");
            System.out.print(pointer.result + "\n");
            pointer = pointer.next;
            counter += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        this.history = this.history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = this.history;
        int sum = 0;
        while(pointer != null){
            sum += pointer.result;
            pointer = pointer.next;
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
        EquationList pointer = this.history;
        int product = 1;
        while(pointer != null){
            product *= pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}