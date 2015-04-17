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

          // YOUR CODE HERE


        int index = 0; // initializing inde
        int sum = 0; //initializing sum
        int carry = 0; // carry is false if carry = 0
        // carry is true of carry = 1

        boolean a = false;
        while (index<32) {
            if (carry==0){;
                if ( getBit(x,index)==0 & getBit(y, index) ==0){
                    a = false; // sets sum to 0

                } else if (( getBit(x,index)==1 & getBit(y, index) ==0 )
                | ( getBit(x,index)==0 & getBit(y, index) ==1)) {
                    a = true; // sets sum to 1
                } else {
                    a = false;  //sets sum to 1
                    carry = 1;
                }
            } else if (carry == 1){
                if ( getBit(x,index)==0 & getBit(y, index) ==0){
                    a = true; // sets sum to 1
                    carry = 0;


                } else if (( getBit(x,index)==1 & getBit(y, index) ==0 )
                | ( getBit(x,index)==0 & getBit(y, index) ==1)) {
                    a = false; // sets sum to 0
                    
                } else {
                    a = true;
                }
            }

            sum = setBit(sum, index, a);
            index = index + 1;

        }


        return sum;
    }

    private int getBit(int x, int i) {
        
        x = x>>i;      
        return (x&1);
    }

    // sets i-th element to 1 if a = true, 
    // sets i-th element to 0 if a = false
    private int setBit(int x, int i, boolean a) {
            if (a == true){
                int num = (1<<i); // this number represents 0000..1..000 with 1 at i-th position
                x = x|num;
            } else {
                int num = ~(1<<i);
                x = x&num;
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
        // YOUR CODE HERE
        int index = 0; // initializing index
        int product = 0; // initializing product

        while (index<32) {

            if (getBit(y, index) == 1){
                product = add(product, (x<<index));
            }
            index = index+1;
        }
    return product;

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

    



    EquationList L = null;
    
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        EquationList L1 = new EquationList(equation, result, L);

        L = L1;

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList L1 = L;
        while (L1 != null){
            System.out.println(L1.equation + " = " + L1.result);
            L1 = L1.next;
        }
        // YOUR CODE HERE

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
        EquationList L1 = L;
        int i = 0;
        while (i<n){
            System.out.println(L1.equation + " = " + L1.result);
            i = i+1;
            L1 = L.next;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE

        EquationList L1 = L.next;
        L = L1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE

        L = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int Sum = 0;
        EquationList L1 = L;
        
        while (L1 != null){
            Sum = Sum + L1.result;
            L1 = L1.next;
        }
        return Sum;
        
        }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int Product = 1;
        EquationList L1 = L;
        
        while (L1 != null){
            Product = Product * L1.result;
            L1 = L1.next;
        }
        return Product;
        
    }
}