import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    int ll = 0;
    EquationList alt;
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
        int count = 0; 
        int finalresult = 0;
        int carry = 0;
        while (count < 32){
            int first = getbit(x,count);
            int second = getbit(y,count);
            int result = (first ^ second) ^ carry;
            carry = (first & second) | (carry & (first ^ second));
            if (result == 1){
                finalresult = setbit(finalresult,count);   
            }
            count = count + 1;
        }
        return finalresult;
        
    }
    public static int getbit(int x, int i){
       int haha = x;
       haha = haha >> i;
       haha = haha & 1; 
       return haha;

    }
    public static int setbit(int x, int i){
        int change = 1;
        change = change << i;
        x = x ^ change;
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
        // YOUR CODE HERE
        int count = 0;
        int finalresult = 0;
        while (count < 32){
            int m = getbit(y,count);
            int result = 0;
            if (m == 0){
               result = 0;
            }
            else {
                result = x;
            }
            result = result << count;
            finalresult = add(finalresult,result);
            count = add(count,1);
        }
        return finalresult;
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
        EquationList neue = new EquationList(equation,result,this.alt);
        this.alt = neue;
        ll = ll + 1;

        

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
        printHistory(ll);
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
        
        int count = 0;
        EquationList what = alt;
        while (count < n && what != null){
            System.out.println(what.equation + " = " + what.result);
            what = what.next;
            count = count + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        alt = alt.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR COD    E HERE
        alt = null;}

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int count = 0;
        int sum = 0;
        EquationList what = alt;
        if (what != null){
           while(count<ll){
            sum = sum + what.result;
            what = what.next;
            count = count + 1;
           }
           return sum;
        }
        else{
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
        int count = 0;
        int product = 1;
        EquationList what = alt;
        if (alt != null){
           while(count<ll){
            product = product*what.result;
            what = what.next;
            count = count + 1;
           }
           return product;
        }
        else{
            return 0;
        }
    }
}