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
        int carry = 1; 
        int sum = 0;

        while (carry != 0){
            sum = x ^ y; //i.e. 101 ^ 010 = 111 (5+2=7)
            carry = (x & y) << 1; //i.e. (111 & 111) << 1 = 1110
            x = sum; 
            y = carry; 
        }   
        
        return sum;
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
        int count = 0;
        int total = 0; 
        if (x<=0 && y >=0){
        	while(count < y){
        		total = add(x, total); 
        		count +=1; 
        	}
        	return total;
        }else if (x>=0 && y<=0){
        	while (count < x){
        		total = add(y, total); 
        		count += 1;
        	}
        	return total;
        }else if (x<=0 && y<=0){
        	while (count > x){
        		total = add(y, total); 
        		count -= 1; 
        	}
        	return (~total) | 1; 
        }else{
        	while (count < x){
        		total = add(y, total); 
        		count += 1; 
        	}
        	return total; 
        }
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

    public EquationList history = null; 

    public void saveEquation(String equation, int result) {
        if (history == null){
            history = new EquationList(equation, result, null); 
        } else {
            history = new EquationList(equation, result, history); //newest is at the front
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
    	EquationList temp = history; 
    	while (temp != null){
    		System.out.println(temp.equation + " = " + temp.result); 
    		temp = temp.next; 
    	}
    	}
    	
       // EquationList temp = history; 
       // while (temp != null){
           // System.out.println(temp.equation + " = " + temp.result); 
            //temp = temp.next;
    
    //public void printAllHelper (EquationList current){
		//if(current.next == null){
			//System.out.println(current.equation + " = " + current.equation); 
		//}else{
			//printAllHelper(current.next); 
			//System.out.println(current.equation + " = " + current.equation); 
		//}
    //}
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp = history; 
        int counter = 0; 
        while (counter < n){
            temp = temp.next; 
            counter += 1; 
        }
        System.out.println(temp.equation + " = " + temp.result); 
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;  
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (history == null){
        	return 0; 
        }else{
        	EquationList temp = history;
        	int cumSum = 0; 
        	while (temp != null){
        		cumSum += temp.result; 
        		temp = temp.next; 
        	}
        	return cumSum; 
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
    	if (history == null){
        	return 1; 
        }else{
        	EquationList temp = history;
        	int cumSum = 1; 
        	while (temp != null){
        		cumSum *= temp.result; 
        		temp = temp.next; 
        	}
        	return cumSum; 
        }
    }
}