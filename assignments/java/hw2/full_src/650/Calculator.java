import list.EquationList;

public class Calculator {
    EquationList lst;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int x_or = x ^ y; //what can be added without carrying
        int both = x & y; //what needs to be carried
        int z; //temporary value
        while (both != 0) {
            both <<= 1;   //Multiplying by two  
            z = x_or ^ both; //setting a temp to what can be added wothout carrying
            both = both & x_or; //changing what needs to be carried
            x_or = z; 
        }
        return x_or; //the answer
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
        int answer = 0; 
        while (y != 0) {
            int check = y & 01;
            if (check != 0) {
                answer = add(answer, x);
            }
        y >>= 1;
        x <<= 1;

        }
        return answer; //the answer
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
        EquationList temp = lst;
        EquationList curr = new EquationList(equation, result, temp);
        lst = curr;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
       EquationList temp = lst;
       while (temp != null)
        {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
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
        EquationList temp = lst;
        while (n > 0)
        {
            if (temp == null)
            {
                return;
            }
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
       lst = lst.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        EquationList temp = lst;
        while (temp != null)
        {
            temp = temp.next;
        }

        lst = temp;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = lst;
        int sum = 0;
        while (temp != null)
        {
            sum += temp.result;
            temp = temp.next;
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
        EquationList temp = lst;
        int sum = 1;
        while (temp != null)
        {
            sum *= temp.result;
            temp = temp.next;
        }

        return sum;
    }
}