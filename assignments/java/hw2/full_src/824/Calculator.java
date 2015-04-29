import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
     public EquationList saveeq;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
       while(x!=0){
 	  int c = x ^ y;
	  x = x & y;
	  x= x << 1;
	  y = c;
	}
       return y;
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
        int k=0;
	/*if(x<y){
	   int temp=x;
	   x=y;
	   y=temp;
	}*/
	
        while(x!=0){
 	   if((x&1)!=0){
	     k=add(k,y);
	     x=x>>>1;
	     y=y<<1;
	   }
	}
        return k;
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
	/*if(saveeq.equation == null){
	    saveeq.equation=equation;
	    saveeq.result=result;
	    return;
	}*/
	saveeq = new EquationList(equation, result, saveeq);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
      EquationList s = saveeq;
	while(s!=null){
	   System.out.println(s.equation + " = " +s.result);
	   s=s.next;
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
        int i=1;
	EquationList m=saveeq;
	if (m==null){
	return;
	}
	while(i<=n){
	   System.out.println(m.equation + " = " +m.result);
           m=m.next;
	   i=i+1;
 	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(saveeq.next==null){
	   saveeq= null;
	}
	saveeq=saveeq.next;
	}

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
	saveeq= null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum=0;
	if (saveeq==null){
	   sum=0;
	}
        else {
           EquationList cc= saveeq;
          while(cc!=null) {
	    sum=add(sum,cc.result);
	    cc=cc.next;
	    }
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
   	int product=1;
        EquationList bb=saveeq;   
	if(bb==null){
	   product=1;
	}
	while(bb!=null){
	   product=multiply(product,bb.result);
	   bb=bb.next;
	   }

 	return product;  
}
}
