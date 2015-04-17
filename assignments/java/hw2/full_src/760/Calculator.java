import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList eList;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int flip = x^y;
    	int mask = x&y;
    	int left;
    	int temp;
    	while(mask!=0){
    		left = mask << 1;
    		temp = flip;
    		flip = flip ^ left;
    		mask = temp & left;
    	}
        return flip;
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
    	Boolean check; //check odd or even
    	int temp;
    	while (y!=1){
        	temp = y >>> 1;
    		check = temp== add(-temp,y); // 1 if y is even, 0 if odd
     		if( check == true){
       	   	  	x = x << 1;
       	   	  	y = y >>> 1;
       	    } else {
       	    	y = add(y,-1);
       	    	return x + multiply(x,y);
       	    }
        }
        return x;
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
    	EquationList temp = new EquationList(equation, result, null);
    	if (eList == null){
    		eList = temp;
    	} else { 
    		EquationList temp2 = eList;
    		while(temp2.next != null){
    			temp2 = temp2.next;
       		}
    		temp2.next = temp;
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
    	if (eList != null){
       	int count = 0;
    	EquationList temp = eList;
        while (temp.next != null){
        	count = count + 1;
        	temp = temp.next;
        }
    	for(int i = 1; i <=count+1; i++){
    		printMyHistory(i);
    	}
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
    	if (eList != null){
       	int count = 0;
    	EquationList temp = eList;
        while (temp.next != null){
        	count = count + 1;
        	temp = temp.next;
        }
        if (count < n){
        	n = count;
        }
    	for(int i = 1; i <=n; i++){
    		printMyHistory(i);
    	}
     }
    }
    public void printMyHistory(int n) {
    	if (eList != null){
    	int count = 0;
    	EquationList temp = eList;
        while (temp.next != null){
        	count = count + 1;
        	temp = temp.next;
        }
        int index = count + 1 - n; // index of the requirement list
        if (index < 0){
        	System.out.println("there is no such history");
        } else {
        temp = eList;
        count = 0;
        while (count < index){
        	temp = temp.next;
        	count = count +1;
        }
        System.out.println(temp.equation + " = " + Integer.toString(temp.result));
        }
    	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
    	if (eList.next == null){
    		eList = null;
    	} else {
    	EquationList temp = eList;
    	EquationList temp2 = temp;
    	while (temp.next != null) {
    		temp2 = temp;
    		temp = temp.next;
    	}
    	temp2.next = null;
    	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
    	eList = null;
    	//int count = 0;
    	//EquationList temp = eList;
        //while (temp.next != null){
        //	count = count + 1;
        //	temp = temp.next;
        //}
    	//for(int i = 1; i <=count+1; i++){
    	//	undoEquation();
    	//}
     }
    	

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if (eList != null){
        	EquationList temp = eList;
        	while (temp.next != null){
        		sum = add(sum,temp.result);
        		temp = temp.next;
        	}
        	sum = add(sum, temp.result);
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
    	int prod = 1;
        if (eList != null){
        	EquationList temp = eList;
        	while (temp.next != null){
        		prod = multiply(prod,temp.result);
        		temp = temp.next;
        	}
        	prod = multiply(prod, temp.result);
        }
        return prod;
    }
}