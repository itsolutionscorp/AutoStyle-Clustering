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
    public int add(int x, int y) {
        while (y != 0) {
            int z = x & y;
            x = x ^ y;
            y = z << 1;
        }
        return x;
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
        if (y > 0){
            y = add(y,-1);
            while (y > 0) {
                x = add(x,a);
                y = add(y,-1);
            }
        }
        else {
            y = add(y,1);
            while (y < 0) {
                x = add(x,a);
                y = add(y,1);
            }
            if (x < 0) {
                x = -x;
            }
        }
        return x;
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
    EquationList A = new EquationList("",0, null);

    public void saveEquation(String equation, int result) {
        if ((A.equation).equals("") && (A.result == 0) && (A.next == null)){
            A = new EquationList(equation,result,null);
        }
        else {
            A = addHistory(equation,result,A);
        }
    }

    public static EquationList addHistory(String equation, int result, EquationList QQQ) {
        if (QQQ == null) {
            QQQ = new EquationList(equation,result,null);
            return QQQ;
        }
        return new EquationList(QQQ.equation,QQQ.result,addHistory(equation,result,QQQ.next));
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if ((!(A.equation).equals("")) && (A.result != 0) && (A.next != null)) {
            int count = 0;
            EquationList B = A;
            while (B.next != null) {
                count = count + 1;
                B = B.next;
            }
            int count2 = 0;
            while (count2 < count) {
                B = A;
                int count3 = count2;
                while (count3 < count) {
                    B = B.next;
                    count3 = count3 + 1;
                }
                System.out.format(B.equation + " = %d\n", B.result);
                count2 = count2 + 1;
            }
            System.out.format(A.equation + " = %d\n", A.result);
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
            int count = 0;
            EquationList B = A;
            while (B.next != null) {
                count = count + 1;
                B = B.next;
            }
            if (count == 0) {
                System.out.format(A.equation + " = %d\n", A.result);
            }
            int count2 = 0;
            while (count2 < count) {
                B = A;
                int count3 = count2;
                while (count3 < count) {
                    B = B.next;
                    count3 = count3 + 1;
                }
                System.out.format(B.equation + " = %d\n", B.result);
                count2 = count2 + 1;
                if (count2 == n) {
                    break;
                }
            }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList B = A;
        while (B.next.next != null) {
            B = B.next;
        }
        B.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        A = new EquationList("",0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (A.next != null) {
            EquationList B = A;
            int sum = 0;
            while (B != null) {
                sum = add(sum,B.result);
                B = B.next;
            }
            return sum;
        }
        else {
            return 0;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (A.next != null) {
            EquationList B = A;
            int prod = 1;
            while (B != null) {
                prod = multiply(prod,B.result);
                B = B.next;
            }
            return prod;
        }
        else {
            return 1;
        }
    }
}