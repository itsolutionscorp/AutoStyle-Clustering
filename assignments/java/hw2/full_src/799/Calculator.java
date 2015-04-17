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
        int counter = 0;
        int carry_over = 0;
        int z = 0;
        int xBit = 0;
        int yBit = 0;
        while (counter < 32){
            xBit = getBit(x, counter);
            yBit = getBit(y, counter);
            if(carry_over == 1){
                if(((xBit == 1 && yBit == 1)||(xBit == 0 && yBit == 0)))
                    {z = combineAt(z, counter, carry_over, xBit, yBit);
                        carry_over = carry_overCalc(xBit, yBit);}
                else{
                    z = combineAt(z, counter, carry_over, xBit, yBit);
                    carry_over = 1;
                }
            }
            else{
                z = combineAt(z, counter, carry_over, xBit, yBit);
                carry_over = carry_overCalc(xBit, yBit);
            }
            
             counter += 1;

        }

        return z;
    }

    public int carry_overCalc(int x, int y){
        int carry = 0;
        if ((x & y) == 1){
                carry = 1;
            }else{
                carry = 0;
            }
        return carry;
    }
    // finds the bit at given location in a num
    public int getBit(int num, int location){
        int processed = ((num >>> location) & 1);
        if(processed == 1){
            return 1;
        }else{
            return 0;
        }
    }
    //takes in a number, and at a given point, changes the bit to another added
    public int combineAt(int modifiedNumber, int point, int carry_over, int xBit, int yBit){
        int add_point = (1 << point);
        if(carry_over == 0){
            if(xBit == 0 && yBit == 1){
            return (modifiedNumber | add_point);
            }else if(xBit == 1 && yBit == 0){
            return (modifiedNumber | add_point);
            }else if(xBit == 0 && yBit == 0){
            return modifiedNumber;
            }else if(xBit == 1 && yBit == 1){
            return modifiedNumber;
            }
        }else{
            if(xBit == 0 && yBit == 1){
            return modifiedNumber;
            }else if(xBit == 1 && yBit == 0){
            return modifiedNumber;
            }else if(xBit == 1 && yBit == 1){
            return (modifiedNumber | add_point);// !!! NEED TO RESEARCH THIS CASE SOME MORE !!!//
            }else if(xBit == 0 && yBit == 0){
            return (modifiedNumber | add_point);
            }
        }
        return modifiedNumber;
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
        int z = 0;
        int counter = 0;
        if (y < 0){
            y = add((~y),1);
            x = add((~x),1);
            while (counter < y){
            z = add(z, x);
            counter += 1;
            }
        }else{
           while (counter < y){
            z = add(z, x);
            counter += 1;
        } 
        }
        
        return z;
    }

    public class EquationSList{
        private EquationList front;
        private EquationList back;
        public int length = 0;
        public EquationSList(){
        this.back = new EquationList("front", -1, null);
        this.front = new EquationList("back", -1, null);
    } 
        public void insertAtEnd(String inEqu, int inResu){
            EquationList curr = front;
            for(int a = 0; curr.next == back; a += 1){
            curr = curr.next;
        }
        curr.next = new EquationList(inEqu, inResu, curr.next);
        length += 1;
        }
        public EquationList locationEquation(int n){
            int i = 0;
            EquationList curr = front;
            while(i < n){
            curr = curr.next;
            i += 1;
            } 
            return curr;
        }
        public void removeAtFront(){
            front.next = front.next.next;
        length -= 1;
        }
        

    }
    
    EquationSList history = new EquationSList();

    //public position = 0;
    //public EquationList pointer = eQList;
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
        history.insertAtEnd(equation, result);
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int count = 1;
        while(count < history.length + 1){
            printHistory(count);
            count += 1;
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
        EquationList current = history.locationEquation(n);
        System.out.println(current.equation + " = " + current.result);

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
       history.removeAtFront();
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = new EquationSList();
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int count = 1;
        int sum = 0;
        while(count < history.length + 1){
            sum += history.locationEquation(count).result;
            count += 1;
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
        if(history.length == 0){
            return 1;
        }
        int count = 1;
        int product = 0;
        while(count < history.length + 1){
            product *= history.locationEquation(count).result;
            count += 1;
        }
        return product;
    }
}