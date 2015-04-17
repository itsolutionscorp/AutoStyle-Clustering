import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList first;
	EquationList last;
	int num=0;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
    	int carry = x&y;
    	int result = x^y;
    	int temp;
    	while (carry !=0){
    		carry = carry << 1;
    		temp = result ^ carry;
    		carry = result & carry;
    		result = temp;
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
       if (x==0){
    	   return 0;
       }
       int result = 0;
       while (y!=0){
    	   if ( (y&1) ==1){
    		   result = add (result, x);
    	   }   
    	   x = x<<1;
    	   y=y>>>1;
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
        EquationList eq = new EquationList(equation,result,null);
        if (num==0){
        	first = eq;
            last = eq;
        }
        else{
        	last.next = eq;
        	last = eq;
        }
        num += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory (num);
     }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList ptr;
        for (int k=1;k<=n;k++){
        	int j = num - k;
        	ptr = first;
    		for (int i=0; i<j; i++){
    			if (ptr!= null){
    				ptr = ptr.next;
    			}
    		}
    		System.out.println(ptr.equation+" = "+ptr.result);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if ((num ==1)||(num==0)){
        	clearHistory();
        }
        else{
        	num --;
        	EquationList ptr = first;
        	for (int i=1;i<num;i++){
        		if (ptr!=null){
        			ptr = ptr.next;
        		}
        	}
        	last = ptr;
        	ptr.next = null;
    	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
    	first = null;
    	last = null;
    	num=0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList ptr = first;
        while (ptr != null){
        	sum += ptr.result;
        	ptr = ptr.next;
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
    	int product = 1;
        EquationList ptr = first;
        while (ptr != null){
        	product *= ptr.result;
        	ptr = ptr.next;
        }
        return product;
    }
}