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

    

    public static void main(String[] args) {
        //add(-15, 8);
        multiply(20000, -5);   
        System.out.println(Integer.toBinaryString(3 >> 31)); 
    }

//right shift by one for each to get the last bit off
//XOR the two bits together --> what we put in the register
//AND the two bits together --> our new carrybit

//to put stuff in the register: shift the new thing by however many times we've looped so far
    //OR together with our current register values!

    public static int add(int x, int y) {
        int carryBit = 0;
        int registerBit = 0;
        int register = 0;
        int tempCarryBit = 0;

        int x_bit = 0;
        int y_bit = 0;

        for (int index = 0; index < 32; index += 1) {
            x_bit = getBit(x); //pull rightmost bit from both ints
            y_bit = getBit(y); 

            tempCarryBit = carryBit; //temporarily store carryBit

            carryBit =  (x_bit & y_bit) | (x_bit & carryBit) | (y_bit & carryBit);//AND together to figure out our carryBit
            registerBit = (x_bit ^ y_bit) ^ tempCarryBit; //XOR together to figure out our registerBit

            registerBit = registerBit << index; // move the register bit over by however many the index is
            register = register | registerBit; // OR it with the current register to see how things pan out

            x = x >> 1; //right-shift both variables to begin add sequence again
            y = y >> 1;      
        }

        return register;
    }

    public static int getBit(int data) {
        return (data & 1);
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public static int multiply(int x, int y) {
        int total = 0;

        for (int index = 0; index < 32; index += 1) {
            if (getBit(y) == 1) {
                total = add(total, x << index); //Add (2^index) * x_value repeatedly to get total
            }
            y = y >> 1;
        }
        return total;
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

    EquationList history; //automatically saves as the null type

    public void saveEquation(String equation, int result) {
        history = new EquationList(equation, result, history);
    }
    

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/


    
    public void printAllHistory() {
        if (history == null) {
            return;
        }
        EquationList pointer = history;
        while (pointer != null) {
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
        if (history == null) {
            return;
        }
        EquationList pointer = history;
        while (n > 0) {
            if (pointer == null) {
                return;
            }
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
            n -= 1;
        }
    }   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history == null) {
            return;
        }
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
        if (history == null) {
            return 0;
        }

        EquationList pointer = history;
        int sum = 0;

        while (pointer != null) {
            sum = add(sum, pointer.result);
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
        if (history == null) {
            return 1;
        }

        EquationList pointer = history;
        int product = 1;

        while (pointer != null) {
            product = multiply(product, pointer.result);
            pointer = pointer.next;
        }
    return product;
    }


}