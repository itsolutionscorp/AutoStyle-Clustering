import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
//Approach 1: Using symbols repeatedly
//    int curr;
//        while ((x&y) != 0){
//            (x&y)<<= 1;
//            curr = (x^y)^(x&y);
//            (x&y) &= (x^y);
//            (x^y) = curr;
//        }    
//        System.out.println(x^y);
//    }

//Approach 2: Using variables
       int a, b, carry;
        a = x & y;
        b = x ^ y;

        while (a != 0) {
            a <<= 1;
            carry = b ^ a;
            a &= b;
            b = carry;
       }
//        System.out.println(xor);
        return b;
    }

//Approach 3: using recursion 
//    int fin = x ^ y; 
//    int carry = (x & y) << 1; 
//    if (carry != 0) {
//        return add(fin, carry);
//    }
//    return fin;
//}

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
    
    int fin =0;
        while (y != 0) // Iterate the loop till y==0
        {
            if ((y & 01) != 0) // Checking for odd or even.
            {
                fin = add(fin, x); 
            }
            x <<= 1;              
            y >>>= 1;             
        }
        return fin;
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
        EquationList li1= new EquationList( equation, result, history);
        history = li1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList iter= history;
        while (iter != null) {
            System.out.println(iter.equation+ " = "+ iter.result);
            iter = iter.next;
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
        EquationList iter= history;
        while (n > 0 && iter != null) {
            System.out.println(iter.equation+ " = "+ iter.result);
            iter = iter.next;
            n = n-1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history= history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all  (entries in our history.
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
        EquationList iter= history;
        int sum = 0;
        while (iter != null) {
            sum += iter.result;
            iter= iter.next;
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
        EquationList iter= history;
        int product = 1;
        while (iter != null) {
            product *= iter.result;
            iter= iter.next;
        }
        return product;
    }
}