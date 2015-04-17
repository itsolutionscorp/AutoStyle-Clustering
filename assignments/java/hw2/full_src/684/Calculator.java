import list.EquationList;

public class Calculator {
        public EquationList history;
        public int sum;
        public int product;
        public EquationList head; 
        public int length;

    public Calculator() {
        history = new EquationList("", 0, null);
        head = history;
        sum = 0;
        product = 1;
        length = 0;
    }

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        int temp, and, xor;
        xor = x ^ y;
        and = x & y;
        while (and != 0) {
            and <<= 1;
            temp = (xor) ^ (and);
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
        // YOUR CODE HERE
        int sum_result = 0;
        while (y != 0) {
            if ((y & 01) != 0) {
                sum_result = x + sum_result;
            }
            x <<= 1;
            y >>>=1;
        } 
        return sum_result;
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
        // YOUR CODE HERE
        if (head.equation == "") {
            head = new EquationList(equation, result, null);
            length += 1;
            return;
        }else {
            EquationList temp_head = new EquationList(equation, result, null);
            temp_head.next = head;
            head = temp_head;
            length += 1;
        }
        
        //history = new EquationList(equation, result, history);

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
        if (head.equation == null) {
            System.out.println("");
        }
        EquationList tracker = head;
        while (tracker != null && tracker.equation != null) {
            if (tracker.equation == "") {
                tracker = tracker.next;
                continue;
            }
            System.out.println(tracker.equation + "" + " = " + Integer.toString(tracker.result));
            tracker = tracker.next;
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
        if (n == 0) {
           System.out.println(""); 
        }else if (n > length) {
            System.out.println("out of range");
        }
        EquationList tracker = head;
        while (n > 1 && tracker != null) {
            n = n-1;
            tracker = tracker.next;
        }
        System.out.println(tracker.equation + " = " + Integer.toString(tracker.result));
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        head = head.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        head = new EquationList("", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (head.equation == null) {
            sum = 0;
        }else {
            EquationList tracker = head;
            while (tracker != null && tracker.equation != null) {
                sum = add(sum, tracker.result);
                tracker = tracker.next;
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
        if (head.equation == null) {
            product = 1;
        }else {
            EquationList tracker = head;
            while (tracker != null && tracker.equation != null) {
                product = multiply(product, tracker.result);
                tracker = tracker.next;
            }
        }
        return product;
    }

    public static void main(String[] args) {
        Calculator test = new Calculator();
        //test save equation
        String test_eq1 = "1 + 2";
        int test_res1 = 3; 
        String test_eq2 = "1 + 3";
        int test_res2 = 4; 
        String test_eq3 = "1 + 4";
        int test_res3 = 5;
        test.saveEquation(test_eq1, test_res1);
        test.printAllHistory();
        System.out.println("-----------------------");
        test.saveEquation(test_eq2, test_res2);
        test.printAllHistory(); 
        System.out.println("-----------------------");
        test.saveEquation(test_eq3, test_res3);
        test.printAllHistory(); 
        System.out.println("-----------------------");
        test.undoEquation();
        test.printAllHistory();
        System.out.println("-----------------------");
        int sum = test.cumulativeSum();
        System.out.println(sum);
        System.out.println("-----------------------");
        int p = test.cumulativeProduct();
        System.out.println(p);
        System.out.println("-----------------------");
        test.printHistory(2);
        System.out.println("-----------------------");
        test.clearHistory();
        test.printAllHistory();


    }

}