import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList last;
    public int lengthOfList= 0;
    public boolean firstCall = true;

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
        int carryOver = 0;
        int result = 0; //all bits are 0 initially
        //to iterate through the 32 bits
        for(int i=0; i<32; i++){
            //get the ith bits of x and y. Using And
            int xIthBit = (1 << i) & x;
            int yIthBit = (1 << i) & y;
            
            //so far, we already know the bit in x and bit in y; we need to know the xor result
            int xorResult = xIthBit ^ yIthBit;

            //since we represent xorResult as an int, and it takes the form like 0000100000, 
            //so we need to make it to be 0 or 1 then it will be easy to compare
            if(xorResult != 0)
                xorResult = 1;
            if(xIthBit != 0)
                xIthBit = 1;
            if(yIthBit != 0)
                yIthBit = 1;

            // Hmm. how come 1 is not true in java..
            int b = xorResult ^ carryOver;
            if(b == 1){
                //set the ith bit of result to be 1. Using OR   
                result = result | (1 << i);
            }   

            //update the carryOver, if two out of three(xthbit,ythbit,carryover) are 1, then carryover should be set to 1;
            if( (xIthBit == 1 && yIthBit == 1) || (xIthBit == 1 && carryOver == 1) || (yIthBit == 1 && carryOver == 1) )
                carryOver = 1;
            else
                carryOver = 0;
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
        //Not efficient enough 
        // int result = 0;
        // for(int i = 0; i<y; i++){
        //     result = add(x,result);
        // }
        // return result;
       
        // eg, 2*3,  3: 0000...11, we will do 2*1 + 2*2.
		int result=0;
		while(y != 0)              
		{
			int check = y & 1;
			//if it's 1, then we add it to result.
			if (check == 1){
				result = add(result, x);
			}     		    
		    x = x << 1;                   
		  	y = y >>> 1;   //must use logical right, otherwise if y is a negative number it will become an infinite loop             		  	
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
        // YOUR CODE HEREn
        // somehow this line cant update the list !! So everything it only contains one thing..Jeez..
        if(firstCall){
            last = new EquationList(equation, result, null);
            firstCall = false;
        }
        else{
            last = new EquationList(equation, result, last);     
        }
        lengthOfList++;
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
        printHistory(lengthOfList);

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
        
        EquationList copyReference = last;
        //EquationList copyReference = last;
        if(copyReference == null)
            return;
        for(int i=0; i<n; i++){        
            System.out.println(copyReference.equation + " = " + copyReference.result); 
            copyReference=copyReference.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        last = last.next;
        lengthOfList -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        last = null;
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
        EquationList copyReference = last;
        while(copyReference != null){
            sum += copyReference.result;
            copyReference = copyReference.next;
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
        int result = 1;
        EquationList copyReference = last;
        while(copyReference != null){
            result *= copyReference.result;
            copyReference = copyReference.next;
        }
        return result;
    }
}