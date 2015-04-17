import list.EquationList;

public class Calculator {
    public int x;
    public EquationList eq_list = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
       

    int result_number = x ^ y; 
    int and = x&y;
    int carry_bit = and << 1; 
    if (carry_bit != 0) {
        return add(result_number, carry_bit);
    }
    return result_number;
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

        int result = 0;
        int counter = y;
        int variable = 0;
        //System.out.println(x);
        //System.out.println(y);

        if( y<0){

            int flipped_y = (~y) | 1;
            result = multiply(x,flipped_y);
            variable = 1;
        }

        if(variable == 0){


        while(counter>0){

            result = add(result,x);
            //System.out.println(result);
            counter = counter - 1;
        }

        }

        if(variable == 1){

            return ((~result) | 1);



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
        if(eq_list == null){
            eq_list = new EquationList(equation,result,null);
        }
        else{
            EquationList temp = new EquationList(equation,result,eq_list);
            eq_list = temp;
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = eq_list;
        while(temp != null){

            System.out.println(temp.equation + " = "+temp.result);
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
        EquationList temp = eq_list;
        
        for(int i =n; i>0;i--){
            System.out.println(temp.equation + " = "+temp.result);
            temp = temp.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        
        EquationList temp = eq_list.next;
        eq_list = temp;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eq_list = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
       int total_sum = 0;
       EquationList temp = eq_list;
       if(eq_list == null){
        return 0;
       }
       while(temp != null){

        total_sum = add(total_sum,temp.result);
        temp = temp.next;



       }
       return total_sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int total_product = 1;
       EquationList temp = eq_list;
       if(eq_list == null){ return 1;}
       while(temp != null){

        total_product = multiply(total_product,temp.result);
        temp = temp.next;



       }
       return total_product;
    }
}