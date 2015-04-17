import list.EquationList;

public class Calculator {
    public EquationList myList;

    public Calculator(){
    }
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
        // YOUR CODE HERE
        int result;    
        while ((x & y) != 0) {
            int temp = x ^ y;
            int temp2 = x & y;
            x = temp2 << 1;
         y = temp;
        }
        result = x | y;
        if (x < 0){
            if (x > y){
                result = ~ result;
                result = add(result, 1);
            }
        //do nothing
        }
        else if (y < 0){
            if (y > x){
                result = ~ result;
                result = add(result, 1);
            }
        //do nothing
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
        int index = 0;
        int result = 0;
        int addThis = 0;
        int originalX = x;
        if ((y & 1) == 1){
                result = add(result,x);
            }
        y = y >>> 1;
        index = 1;
        while (y != 0){
            if ((y & 1) == 1){
                addThis = x << index; 
            }
            y = y >>> 1;
            result = add(result, addThis); //multiply by 2
            addThis = 0;
            index += 1;
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
        if (myList == null){
            myList = new EquationList(equation,result,null);
        }
        else {
            EquationList current_list = myList;
            while(current_list.next != null){
                current_list = current_list.next;
            }
            current_list.next = new EquationList(equation,result,null);
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

        if (myList == null){
            return;       
        }
        int size = myList.getSize();
        size = size + 1;
        int index = 1;
        while (index < size){
            printHistory(index);
            index += 1;
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
        // YOUR CODE HERE.
        int index = myList.getSize() - n;
        EquationList a = this.myList;
        while (index != 0){
            a = a.next;
            index = index - 1;
        }
        System.out.println(a.equation + " = " + a.result);
        /*
        EquationList a = myList;
        while (n != 0){
            a = a.next;
            n = n - 1 ;
        }
        System.out.println(a.equation + " = " + a.result);
        */
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (myList.next == null){
            myList = null;
            return;
        }
        EquationList pointerList = myList;
        while (pointerList.next.next != null){
            pointerList = pointerList.next;
        }
        pointerList.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        myList = null;
        return;
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
        EquationList referenceList = myList;
        while (referenceList != null){
            sum = add(referenceList.result,sum);
            referenceList = referenceList.next;
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
        if (myList.getSize() < 2){
            return 0;
        }
        else {
            int product = 0;
            EquationList referenceList = myList;
            int first = referenceList.result;      
            referenceList = referenceList.next;
            product = multiply(referenceList.result,first);
            referenceList = referenceList.next;
            while (referenceList != null){
                product = multiply(referenceList.result,product);
                first = referenceList.result;
                referenceList = referenceList.next;
            }
        return product;
        }
    }
}