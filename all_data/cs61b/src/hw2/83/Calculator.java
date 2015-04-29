import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList mylist;

    
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
        // Check if one int is 0
        if (y == 0) { //base case set up s.t. y is the remainder bitwise int sent to the next recursive call
            // so once we hit when there is nothing left to carry we return our int back up the recursive call
            return x;
        } else {
	    // new int to add is generatred by the xor operator, and the other addend is given by the remainder of the two
            return add((x ^ y), ((x & y) << 1));
	}        
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
        
	//A method that only works for positive integers
	// Int to store result of calculation
        int result = 0;
	// catch if a 0 is fed to the multiply function
	if (y == 0 || x ==0) {
	    return 0;
	} else {
	    //if (y < 0 && x < 0) {
		//y = -y;
		//x = -x;
	    //}
            while (y != 0) {
                if ((y & 1) != 0) {
		    result = result + x;
	        }
	        x = x << 1;
                y = y >>> 1;
		//System.out.println("Y better be decreasing " + y);
	        //System.out.println("Geeting derped here");
		
	    }
  	}
        return result;
    
        

	// Do something simpler
	/**
	if (y == 0 || x ==0) {
	    return 0;
        }
	if (y > 0) {
	    return add(x,multiply(x,add(y,-1)));
	}
	if (y < 0) {
	    return -multiply(x,-y);
	}
	return x;
	*/   
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
	EquationList ptr = mylist;
	if (mylist == null) {
	    mylist = new EquationList(equation,result,null);
        } else {
	    while (ptr.next != null) {
		ptr = ptr.next;
            }
	    ptr.next = new EquationList(equation,result,null);
	}

	//mylist = new EquationList(equation
        //mylist.equation = equation;
        //mylist.result = result;
        //mylist.next = null;


    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	EquationList tmp = mylist;
	int counter = 0;
	int total = 0;
	if (tmp == null) {
	    //System.out.println("No history");
	} else {
	    while (tmp.next != null) {
                counter = add(counter,1);
		tmp = tmp.next;
	    }
	    
	    total = counter + 1;
	    //System.out.println("Length of LL: " + counter);
	    for (int i = 0; i < total; i++) {
		tmp = mylist;
		for (int j = 0; j < counter; j++) {
		    tmp = tmp.next;
   		}
		
		System.out.println(tmp.equation + " = " + tmp.result);
		counter = add(counter, -1);
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
        // YOUR CODE HERE
	EquationList ptr = mylist;
	// Count how long the linked list is
	int counter = 0;
	
	//EquationList result = new EquationList("3",3,null);
	if (mylist == null) {
    	    //System.out.println("No recent history");
	} else {
	    while (ptr.next != null) {
	        counter = add(counter, 1);
		//System.out.println(counter);
		ptr = ptr.next;
	    }
	    //Now counter contains the number of tails I can follow until I hit the last ode
	    //counter = add(counter, 1);
	    //System.out.println(counter);
	    for (int i = 0; i < n; i++) {
	        ptr = mylist;
		for (int j = 0; j < counter; j++) {
		    ptr = ptr.next;
		}
		if (counter >= 0) {
		    System.out.println(ptr.equation + " = " + ptr.result);
		}
		counter = add(counter, -1);
		//System.out.println(counter);
	    }
	}

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
	EquationList ptr = mylist;
	if (mylist == null) {
	    //System.out.println("No recent equation to remove");
	} else if (mylist.next == null) {
	    mylist = null;
	} else {
	    while (ptr.next.next != null) {
		ptr = ptr.next;
	    }
	    ptr.next = null;
	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
	mylist = null;
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
	EquationList ptr = mylist;
	if (ptr == null) {
	    return 0;
	} else {
	    while (ptr.next != null) {
	        sum = add(sum, ptr.result);
		ptr = ptr.next;
	    }
	    sum = add(sum, ptr.result);
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
	EquationList ptr = mylist;
	if (ptr == null) {
	    return 1;
	} else {
	    while (ptr.next != null) {
		//System.out.println("Ptr.result is " + ptr.result);
		//System.out.println("Product is " + product);
	        product = multiply(product, ptr.result);
		ptr = ptr.next;
	    }
	    product = multiply(product, ptr.result);
	}

	return product;
    }
}
