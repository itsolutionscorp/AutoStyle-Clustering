import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null;
    int length;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/


    public int add(int x, int y){
            int x1 = x;
            int y1 = y;
            int temp = 0;
        while (y1 != 0) {
            temp = x1 & y1;
            x1 = x1 ^ y1;
            y1 = temp << 1;
        }      
        return x1; 
    }

    
     /** TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/

    public int multiply(int x, int y) {
        int x1 = x;
        int y1 = y;
        int val = 0;
        int pos = 0; 
        while (pos < 32){        
            if (getBit(y1, pos) == 1){
                val = add(val, (x1 << pos));
            }         
            pos = pos + 1;
        }
        return val;
    }

    private int getBit(int y, int position){
        int y1 = y;
        y1 >>= position;
        int bit = (y1 & 1);
        return bit;
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
        EquationList saver = new EquationList(equation, result, history);
        history = saver;  
        length += 1; 
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList pointer = history;
        while (pointer != null){
            System.out.println(pointer.equation + " = " + pointer.result); 
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
       EquationList pointer = history;
       int count = 0;
        if (pointer == null){
            System.out.println("Error: cannot acces history");
        }    
       else if (n <= 0){
            System.out.println("Error: cannot access history less than 1");  
       }
       else if (n == 1){
            System.out.println(pointer.equation + " = " + pointer.result);
       }

       
       else{
            while(pointer != null){
                count += 1;
                pointer = pointer.next;
            }
            pointer = history;
            if (count < n){
               System.out.println("Error: cannot access history"); 
            } 
            else{  
                for (int i = 1; i < n; i++){
                    pointer = pointer.next;
                    }
                System.out.println(pointer.equation + " = " + pointer.result);
            } 
       }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = history;
        int sum = 0;
        while(pointer != null){
            sum = add(pointer.result, sum);
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
        EquationList pointer = history;
        int product = 1;
        while(pointer != null){
            product = multiply(pointer.result, product);
            pointer = pointer.next;
        }
        return product;    
    }
}