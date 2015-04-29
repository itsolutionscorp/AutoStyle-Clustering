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
        int a = 0;
        while (a < 32) {
            if (getBit(x, a) == 1 && getBit(y, a) == 1 && a<32) {

                if(getBit(x,a+1)==1 && getBit(y,a+1)==1 && a<31) {
                    int b = a+1;
                    x = setBit(x, a+ 1);
                    while ((getBit(x, b + 1) == 1) && (getBit(y, b + 1) == 1) && b<31) {
                        x = setBit(x, b + 1);
                        b++;

                    }
                    if (getBit(x, b + 1) == 1 && getBit(y, b + 1) == 0 && b<31) {
                        y = setBit(y, b + 1);

                    }
                    else if (getBit(x, b + 1) == 0 && getBit(y, b + 1) == 1 && b<31) {
                        x = setBit(x, b + 1);
                        }
                    else if (getBit(x, b + 1) == 0 && getBit(y, b + 1) == 0 && b<31){
                        x = setBit(x, b + 1);
                        }
                }
                else if (getBit(x, a + 1) == 1 && getBit(y, a + 1) == 0&& a<31)  {
                    y = setBit(y, a + 1);
                }
                else if (getBit(x, a + 1) == 0 && getBit(y, a + 1) == 1&& a<31) {
                    x = setBit(x, a + 1);
                }
                else if (getBit(x, a + 1) == 0 && getBit(y, a + 1) == 0&& a<31)  {x = setBit(x, a + 1);
            }


        }
        a++;

    }


    return x^y;

    }

    private int getBit(int i, int j){

        if ((i&(1<<j))!=0 && j<=33)
            return 1;
        else return 0;

    }

    private int setBit(int x, int y){

        return (x^(1<<y));}


    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        int counter = y;
        int product = 0;
        if (counter >0){

            while (counter>0){
                product = add(product, x);
                counter-=1;
            }

        }
        else if (counter<0){
                counter = add((~counter),1);
                while (counter>0){
                    product = add(product, x);
                    counter-=1;

                }
                product = add((~product),1);
            }

        return product;
    }
    EquationList History = null;
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
        History = new EquationList(equation, result, History);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList x = History;
        while (x!=null){
            System.out.println(x.equation + " = " + x.result);
            x=x.next;
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
        EquationList x = History;
        int m = 0;
        while (m<n && x!=null){
            System.out.println(x.equation + " = " + x.result);
            x=x.next;
            m++;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        History = History.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        History = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
       EquationList x = History;
        int sum = 0;
        while (x!=null) {
            sum += x.result;
            x = x.next;
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
        EquationList x = History;
        int product = 1;
        while (x!=null) {
            product *= x.result;
            x = x.next;
        }
        return product;
    }
}

