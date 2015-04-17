import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList holder;//so in can use the equationlist class
    public int counter = 0 ;//to monitor how many times it happens


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
        int var1=x ,var2=y,var3=0;
        while(var2 != 0 ){
            var3 = var1&var2;//carryon
            var1 = var1^var2;//sum them up
            var2 = var3 <<1;//shift carryover

        }
        return var1;
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
        int a=x, b=y, result=0;

        while(b!=0){
            if ((b & 1) == 1){
                result = add(result,a);
            }
            a = a << 1;
            b = b >>> 1;
        }  
        return result;
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
        // set history to be in place of null
        holder = new EquationList(equation,result,holder);
        counter = counter + 1; //increment the counter

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
        printHistory(counter);// to count how many times passing it into printHistory 

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
        // always make sure you cater for the previous class
        EquationList new_holder = holder;
        int val = n;
        while(val != 0 ){
            if (holder != null && new_holder != null ){

                    System.out.println(new_holder.equation + " = " + new_holder.result);
                    new_holder = new_holder.next;//this moves from the first point to the next
                   
                }
                val = val - 1;//decreasing 
            }//to make sure null will not be printed
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        //want to make the latest element null
        //EquationList new_holder = holder;
        //new_holder.next = new_holder;
        //new_holder.next = null;
        if ( holder != null){
            holder = holder.next;//getting rid of the first element 
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
           holder = null;// clearing the linked list
   }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        EquationList temp = holder;
        while(temp != null){
            sum = temp.result + sum;
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
        // YOUR CODE HERE
         int product = 1;
        EquationList temp = holder;
        while(temp != null){
            product = temp.result * product;
            temp = temp.next; 
        }

        return product;
 
    }
}