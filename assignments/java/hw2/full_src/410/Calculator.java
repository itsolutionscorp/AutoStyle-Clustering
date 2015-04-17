import list.EquationList;

public class Calculator {
    EquationList equationList = null;  

    // YOU MAY WISH TO ADD SOME FIELDS
    
    


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int a, int b){
        int total = a ^ b; 
        int carry = a & b;  

        while(carry !=0){
            int carryShift = carry<<1; 
            carry = carryShift & total; 
            total = total^carryShift; 
          
        }
        return total; 
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
        int total = 0; 
        int var = y; 
        int b = x;
      
        while (var!=0){
            if ((1 & var) !=0){
                total += b; 
            }
            var = var>>1; 
            b = b<<1; 
        }
        return total; 
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

        equationList = new EquationList(equation, result, equationList); 
    }



    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList copylist= equationList; 

        while (copylist != null){
       
       System.out.println(copylist.equation + " = " + copylist.result);
       copylist = copylist.next; 

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
         EquationList copylist= equationList;
         int i = 1;  
        while (i <n ){
         copylist = copylist.next; 
         i +=1;
            }
            System.out.println(copylist.equation + " = " + copylist.result); 
       
    
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/

    public void undoEquation() {
           equationList = equationList.next;
      
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equationList = null;  
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {

        int sum = 0; 
        EquationList copylist= equationList; 

        while (copylist != null){
            sum += copylist.result; 
            copylist = copylist.next; 
        } 
        // YOUR CODE HERE
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
        EquationList copylist= equationList; 

        while (copylist != null){
            product *= copylist.result; 
            copylist = copylist.next; 
        } 
        // YOUR CODE HERE
        return product;
        // YOUR CODE HERE
    
    }

}

//         public int add(int x, int y) {
     
//         int num = 0;
//         int carry = 1;  
//         int g = 0; 
//         for(int i = 0; x<32; x+=1){
//             System.out.println(getBit(x, i)); 
//             System.out.println(getBit(y, i));
//             g = getBit(x, i) ^ getBit(y, i);
//             System.out.println(g);  
//             // if (carry!= 0){
//             //     // if (getBit(x, i) ^ getBit(y, i)){
//             //     //     carry = 1; 
//             //     //     g = 0; 

//             //     }
//                 if (getBit(x,i) ==1 && getBit(y, i) == 1){
//                     carry = 1; 
//                     g = 1; 
//                 }
//                 else {
//                     carry = 0; 
//                     g = 1; 
//                 }
            
          
//             // g = carry ^ g; 
//             // System.out.println(g);
            

//             // carry = ( getBit(x, i) & getBit(y, i) )| (getBit(x, i) & carry )| (getBit(y, i) & carry); 
//             System.out.println(carry);

//             num = num + (g * 2^i); 
//             System.out.println("num: " + num); 
      
// }        

//             return g; 
//         }

        

        //     public int add(int x, int y){

        //         int alpha = x^y; 
        //         System.out.println(alpha); 
        //         int beta = x&y<<1; 
        //         System.out.println(beta); 
        //         while (beta!=0){
        //             add(alpha, beta); 
        //         }
        //         return alpha; 
        //     }

        // public int getBit(int x, int position){

        //     int a = x >> position; 
        //     int b = a%2; 
        //     return b;  

        // }
/*
    EquationList copylist= equationList;
      
        while (copylist.next.next!= null) {
         copylist = copylist.next; 
            }

            copylist.next = null; */




