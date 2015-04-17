import list.EquationList;

public class Calculator {
    private EquationList history;
    private int N; // number of equations in list
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int a = x;
        int b = y;
        int and = a & b;
        int xor = x ^ y;
        
        while (and != 0) {
            and <<= 1;
            int temp = xor ^ and;
            and &= xor;
            xor = temp;
        }
        return xor;
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
        int a = x;
        int b = y;
        int result = 0;
        while (b != 0) {
            if ((b & 01) != 0) {
                result = result + a;
            }
            a <<= 1;
            b >>>= 1;
        }
        return result;
    }
    
    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and
     * the corresponding result.
     * Note: You only need to save equations, not other commands. See spec for
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    
    
    public void saveEquation(String equation, int result) {
        
        // if first time adding equation to history
        if (history == null) {
            history = new EquationList(equation, result, null);
        } 
        
        // add the new equation at the front of the list
        // for ease of printing out the equations in order 
        // of most recent to oldest
        else {
            
            // store current history in temporary EquationList
            EquationList tempList = history; 
            
            // replace current history with a new EquationList with the only equation
            // being the most recent equation
            history = new EquationList(equation, result, null);
            
            // set history.next as the original history that we stored in templist
            // this method prepends the equations to the list rather than appending it
            history.next = tempList;
        }
        N++; // increment the number of equations 
    }
    
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line. Please print in
     * the following format:
     * Ex "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(N);
    }


/**
 * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
 * printHistory() prints each equation (and its corresponding result),
 * most recent equation first with one equation per line. A maximum of n
 * equations should be printed out. Please print in the following format:
 * Ex "1 + 2 = 3"
 **/
public void printHistory(int n) {
    int i = 0;
    if (n == 0)
        return;
    while (i < n) {
        System.out.println(history.equation + " = " + history.result);
        history = history.next;
        i++;
    }
}


/**
 * TASK 6: CLEAR AND UNDO
 * undoEquation() removes the most recent equation we saved to our history.
 **/
public void undoEquation() {
    if (history == null)
        return;
    history = history.next; // new history is a list minus the very first equation stored
}

/**
 * TASK 6: CLEAR AND UNDO
 * clearHistory() removes all entries in our history.
 **/
public void clearHistory() {
    if (history == null)
        return;
    history = null; // reset everything in history 
}

/**
 * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
 * cumulativeSum() computes the sum over the result of each equation in our
 * history.
 * @return the sum of all of the results in history
 **/
public int cumulativeSum() {
    int sum = 0;
    if (history == null)
        return 0;
    else {
        int i = 0;
        while (i < N) {
            sum += history.result;
            history = history.next;
            i++;
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
    int product = 0;
    if (history == null)
        return 0;
    else {
        int i = 0;
        while (i < N) {
            product *= history.result;
            history = history.next;
            i++;
        }
    }
    return product;
}
}
