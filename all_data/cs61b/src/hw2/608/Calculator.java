import list.EquationList;

public class Calculator {
    EquationList list;
    boolean started = false;
    int number = 0;
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
        int a = x ^ y;
        int mask = x & y;
        mask = mask << 1;
        while(mask != 0)
        {
           int b = a ^ mask;
            mask = mask & a;
            mask = mask << 1; 
            a=b;
        }
        return a;
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
        if (y < 0) return multiply(-x, -y);
        if (y == 0) return 0;
        if (y==1) return x;
        int top = 128;
        int temp = y ^ top;
        int count = 7;
        while (temp >= top && count > 0)
        {
            top =top >>>1;
            temp = y ^top;
            count = add(count, -1);  
        }
        int extended = x << count;
        y = temp;
        return add(extended, multiply(x,y));
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
        if(started)
        {
            list = new EquationList(equation, result, null);
            number++;
        }
        else
        {
            list = new EquationList(equation, result, list);
            number++;
        }
        return;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(number);
        return;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int going = 0;
        String print = "";
        EquationList ref = list;
        while(going < n && ref != null)
        {
            print = print + ref.equation + " = " + Integer.toString(ref.result) + "\n";
            going= add(going, 1);
            ref = ref.next;

        }
        System.out.print(print);
        return;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        list = list.next;
        return;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        list = null;
        return;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList ref = list;
        int sum = 0;
        while (ref != null)
        {
            sum= add(sum,ref.result);
            ref = ref.next;
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
        EquationList ref = list;
        int product = 1;
        while (ref != null)
        {
            product= multiply(product,ref.result);
            ref = ref.next;
        }
        return product;
    }
}