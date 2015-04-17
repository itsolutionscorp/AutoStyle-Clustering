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
    private EquationList saver;
    public int add(int x, int y) {
        // YOUR CODE HERE
        int place1 = x;
        int place2 = y;
        int o = place1 ^ place2;
        int t;
        int a = place1 & place2;
        
        while (a != 0) {
            a <<= 1;
            t = o ^ a;
            a &= o;
            o = t;
        }
        return o;
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
        /*int place1 = x;
        int place2 = y;
        int fin = 0;
        while (place2 != 0){
            if ((place2 & 01) != 0){
                fin += place1;
            }
            place1 = place1 << 1;
            place2 = place2 >> 1;
        }
        return fin;*/
        /*int f = 0;
        while (y != 0){
            f = add(f, x);
            //y = add(~y,1);
            y--;
        }
        return f;*/
        /*int t = 1;
        int f = 0;
        if (x < 0){
            x = add(~x, 1);
            y = add(~y, 1);
        }
        while (x >= t && y !=0){
         
            if ((x & t) != 0){
                f = add(y,f);
                y <<= 1;
                t <<= 1;
            }
        }
        return f;*/
        if (x == 0 || y == 0){
            return 0;
        }
        else if (x == 1){
            return y;
        }
        else if (y == 1){
            return x;
        }
        int r = 0;
        while (y != 0){
            if ((y & 1) != 0){
                r = add(r,x);
            }
            x <<= 1;
            y >>= 1;
        }
        return r;
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
        /*if (f == null){
            EquationList l = new EquationList(equation, result, null);
            return
        }
        else{
            f.tail = saveEquation(equation, result, f.tail);
        }*/
        saver = new EquationList(equation, result, saver);
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
        EquationList p = saver;
        while(p != null){
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
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
        // YOUR CODE HERE
        EquationList t = saver;
        while(t != null && n != 0){
            System.out.println(t.equation + " = " + t.result);
            n--;
            t = t.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        saver = saver.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (saver != null){
            saver = saver.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int t = 0;
        EquationList p = saver;
        while ( p != null){
            t += p.result;
            p = p.next;
        }
        return t;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int t = 1;
        EquationList p = saver;
        while ( p != null){
            t *= p.result;
            p = p.next;
        }
        return t;
    }
}