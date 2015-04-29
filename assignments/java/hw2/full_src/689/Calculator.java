import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = new EquationList (" no more history  " , 404 , null); 
    public int hsize=0;



    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (y==0){
            return x; // recursive base case 
        }
        return add( x^y , (x & y) << 1); // recursive stuff -- eventually (x & y) << 1 will become 0 
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
        int p = x;
        if (x >= 0 && y >= 0){  // when x and y are positive 
            
        while (y>1){
            p = add(p , x);
            y = add(y , -1); 
        }
       
    }
        if ( x <= 0 && y <= 0 ){ // when x and y are negative
            p= Math.abs(x);
            y = Math.abs(y);
            x = Math.abs(x);

        while (y>1){
            p = add(p , x);
            y = add(y , -1); }

            
        }

        else if ( y < 0 && x >= 0 ){ // when y is negative and x is pos
            while (y < 1){
            p = add(p , -x);
            y = add(y , 1); }
        }

        else {
            while (y>1){ // if x is neg and y is pos 
            p = add(p , x);
            y = add(y , -1); 
        }
        }
        return p; 
        }

    /*
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/

    public void saveEquation(String equation, int result) {
   
        history = new EquationList (equation, result, history); // adds most recent entry to the head of equation list 
        hsize = hsize + 1 ; // records the new size of history Equationlist
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int i = 0; 

        EquationList h = new EquationList( history.equation, history.result, history.next); // creates disposable h Equation list so we don't have to destroy the history 

        while (i < hsize){ // prevents from attempt beyond length of history

            System.out.println ( h.equation + " = " + h.result ); // prints most recent
            h=h.next; // takes most recent out of psuedo history 
            i++;
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

        int i = 0 ;

       EquationList h = new EquationList( history.equation, history.result, history.next);

        while (i < n-1 && i < hsize ){ //makes sure n isn't longer than history 

            System.out.println ( h.equation + " = " + h.result );
            h=h.next;  
            i++; 

        }

    
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next ; // knocks most recent entry off 
        hsize = hsize - 1; // records new size of history 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = new EquationList (" no more history  " , 404 , null); // i think im using a sentinel node here
        hsize = 0; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int cumSum = 0; 
        int i = 0; 
        EquationList h = new EquationList( history.equation, history.result, history.next); 
        
        while (i < hsize){
            cumSum = add( cumSum , h.result);
            h = h.next ; 
            i++; 
        }

        return cumSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int cumProd = 1; 
        int i = 0;
        EquationList h = new EquationList( history.equation, history.result, history.next);

        while (i < hsize){
            cumProd = multiply(cumProd, h.result);
            h = h.next; 
            i++;
        }

        return cumProd;
    }
}