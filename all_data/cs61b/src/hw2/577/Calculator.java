import list.EquationList;

public class Calculator {
    //comment for commit! sdflkjhsdlkjhfslkjhfd
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (x != 0){
            int commondigits = y & x; 
            y = y ^ x;
            x = commondigits << 1;
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
        int power_of_2 = 1;
        int counter = 0;
        if (x == 0 || y == 0) {
            return 0;
        }
        int originalx = x;
        int originaly = y;
        x = Math.abs(x);
        y = Math.abs(y);
        while (power_of_2 < y) {
                power_of_2 = power_of_2 << 1;
                counter += 1;
        }
        power_of_2 = power_of_2 >> 1;
        counter = counter - 1;
        int index = y - power_of_2;
        int answer = x;
        answer = answer << counter;
        while (index > 0) {
            answer = add(answer, x);
            index = index - 1;
        }
        if ((originalx < 0 || originaly < 0) && !(originalx < 0 && originaly < 0)) {
            answer = -answer;
        }
        return answer;
    }

    // Instance variable for the Calculator's history
    EquationList history;

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
        this.history = new EquationList(equation, result, this.history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/

    //Creates an empty EquationList
    public EquationList emptylist() {
        EquationList emptylist = null;
        return emptylist;
    }

    public void printAllHistory() {
        int counter = 0;
        EquationList counterhelper = this.history;
        while (counterhelper != null) {
            counter = counter + 1;
            counterhelper = counterhelper.next;
        }
        this.printHistory(counter);
    
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int index = 0;
        EquationList print = this.history;
        while (index < n && print != null) {
            System.out.println(print.equation + " = " + print.result);
            print = print.next;
            index = index + 1;
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
        this.history = emptylist();
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if (this.history == null) {
            return sum;
        }
        EquationList cumulator = this.history;
        while (cumulator != null) {
            sum = sum + cumulator.result;
            cumulator = cumulator.next;
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
        int product = 1;
        if (this.history == null) {
            return product;
        }
        EquationList cumulator = this.history;
        while (cumulator != null) {
            product = product * cumulator.result;
            cumulator = cumulator.next;
        }
        return product;
    }
}