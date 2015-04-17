import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
   
    public EquationList original; 

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
        
	int sum = 0;
        int carry = x & y;
	int xor = x ^ y;

        while (carry != 0) { // check if there is carry
        carry <<= 1; // make carry
        sum = carry ^ xor; //adjust the sum
        carry &= xor; //check if current carry did the job
        xor = sum;   
        

        }
        return xor;
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
     
     int toggle = 0;
     int counter = 0;
     int mask = 0;
     int sum = 0;
     Calculator calcu = new Calculator();
     while (counter < 32) {
          toggle = calcu.currbit(y, counter);
          if (toggle == 1) {
             toggle =- 1;
          }
             mask = x & toggle;
             mask <<= counter; //place holder         
             
             sum = calcu.add(sum, mask);
          
          counter += 1;
     }
      return sum;
}
    public int currbit(int binary, int index ) { 
       binary >>>= index; 
       binary = binary & 1;
       return binary; 
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
        EquationList EQL = new EquationList(equation, result, null); 
        if (original == null) {
             original = EQL;
        }
        else {
            EQL.next = original; 
            original = EQL;
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
        
       //reset the pointer
       printHistory(Integer.MAX_VALUE);
        
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
      
       EquationList tracker = original;
      
            while (tracker != null)  {
            System.out.println(tracker.equation + " = " + tracker.result);
            tracker = tracker.next;
           }
   
  }


    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
     EquationList tracker = original; 
     if (original != null) {

            original = original.next;
       }
}

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        original = null;
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
        EquationList tracker = original;
        Calculator cumulative = new Calculator();
        sum = cumulative.add(sum, tracker.result);
        while ((tracker.next != null) && (tracker != null)) {
           tracker = tracker.next;
           sum = cumulative.add(sum, tracker.result);
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
        EquationList tracker = original;
        Calculator cumulative = new Calculator();
        product = cumulative.multiply(product, tracker.result);
        while ((tracker != null) && (tracker.next != null)) { 
           tracker = tracker.next;
           product = cumulative.multiply(product, tracker.result);
        }
        return product;
    }
}

