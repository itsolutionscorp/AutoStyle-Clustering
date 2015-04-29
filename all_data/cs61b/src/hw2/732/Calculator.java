import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = new EquationList(null, 0, null); 

    public static int getbit(int number, int position) {
        int shifted = (number >> position);
        return shifted & 1;
    }

    public static int setbit(int number, int position) {
        return number | (1 << position);
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
        int position = 0;
        int carryover = 0;
        int answer = 0;
        while (position < Integer.SIZE){
            int a= getbit(x, position);
            int b = getbit(y, position);
            int result = a ^ b ^ carryover;
            //System.out.println("current numbers are " + a + " and " + b + " and current carryover is " + carryover + " xor is " + result);
            if (result != 0){
                answer = setbit(answer, position);
                int next = a & b & carryover;
                if (next != 0){
                    carryover = 1;
                } else{
                    carryover = 0;
                }
            } else {
                int cResult = a | b | carryover;
                if (cResult != 0){
                    carryover = 1;
                } else {
                    carryover = 0;
                }
            }
            //System.out.println("carryover is now " + carryover + " answer is now " + answer);
            position += 1;
        }
        return answer;
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
        if (y == 0){
            return 0;
        }
        else if (y < 0) {
            if (x < 0){
                return multiply(-x, -y);
            }
            return multiply(y, x);
        } 
        else {
            int counter = 0;
            int answer = 0;
            while (counter < y){
                //System.out.println(answer + " " + x);
                answer = add(answer, x);
                counter += 1;
                //System.out.println(answer);
            }
            return answer;
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
        // YOUR CODE HERE
        EquationList curr = history; 
        while (curr.next != null){
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
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
        EquationList curr = history; 
        for (int a = 0; a < n && curr.next != null; a++){
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
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
        history = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int total = 0;
        EquationList curr = history; 
        while (curr.next != null){
            total += curr.result;
            curr = curr.next;
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
        int total = 1;
        EquationList curr = history; 
        while (curr.next != null){
            total *= curr.result;
            curr = curr.next;
        }
        return total;
    }
}