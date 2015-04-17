import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqList;
    int check =0;
    
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
        int inmind= 0;
        int a, b, n;
        int result=0;
        for (int i=0; i<32; i++){
            a= (x>>i)&1;
            b = (y>>i)&1;
            result =result |(a^b)<<i;
            if (inmind==1){
                n=(result>>i)&1 ;
                result = result ^ (1<<i);
                if (n==0)
                    {inmind=0;}
            }  
            n= a&b;          
            if  (n ==1)
                {inmind=1;}
        }
        return result;
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
        int result=0;
        int p=0;
        for (int i=31; i>=0; i--){
            p = y & (1<<i);
            if (p!=0){
                result =add(result, x<<i);
            }
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
        if (check ==0){
            eqList = new EquationList(equation, result, null);
            check =1;
        }  
        else{
            eqList = new EquationList (equation, result, eqList); 
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
        // YOUR CODE HERE
        printHistory(1000);
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
        if  (eqList== null){return;}               
        else {
            EquationList temp = eqList;                                     
            while(n!=0){ 
                if  (temp!= null){    
                    System.out.println(temp.equation + " = " + temp.result);
                    temp = temp.next;
                    n--; 
                }
           else { return;}          
            }    
        }
    }  

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (eqList!= null){
            eqList = eqList.next;
        }
        else return;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (eqList != null){
            eqList = eqList.next;
        }        
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum =0;
        EquationList temp = eqList; 
        while(temp !=null){
            sum = add(sum, temp.result);
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
        int product= 1;
        EquationList temp = eqList;
        while(temp !=null){
            product = multiply (product, temp.result);
            temp = temp.next;
        }
        return product;
    }
}