import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public int getBit(int n, int k){
        return (n>>k) & 1;
    }
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
        int n = 0;
        int sum = 0;
        boolean carry = false;
        while(n<32){
            int num1 = this.getBit(x, n);
            int num2 = this.getBit(y, n);
            if (carry == true && (num1 & num2)==1){
                //1
                sum = sum | 1 << n;
                carry = true;
            }
            else if(carry == true && (num1 ^ num2)==1){
                //0
                carry = true;
            }
            else if(carry == true && (num1 & num2)==0){
                carry = false;
                //1
                sum = sum | 1 << n;
            }
            else if((num1 & num2)==1){
                carry = true;
                //0
            }
            else if((num1 ^ num2)==1){
                //1
                sum = sum | 1 << n;
            }
            else if( (num1 & num2)==0){
                //0

            }
            n += 1;
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
        // YOUR CODE HERE
        int count = 0;
        int count1=0;
        int sum = 0;
        int adder = 0;
        int num1 = 0;
        int num2=0;
        if(x<0 && y>0){
            int flipped = multiply(this.add(~x, 1), y);
            return add(~flipped, 1);
        }
        else if(x>0 && y<0){
            int flipped = multiply(x, this.add(~y, 1));
            return add(~flipped, 1);
        }
        else if(x<0 && y<0){
            return multiply(this.add(~x, 1), this.add(~y,1));
        }
        while (count<32){
            num1 = this.getBit(x, count);
            adder = 0;
            count1=0;
            while(count1<32){
                num2 = this.getBit(y, count1);
                if((num1 & num2) == 1){
                    adder = adder | 1 << this.add(count1, count);
                }
                count1 += 1;
            }
            sum = this.add(sum, adder);
            count +=1;
        }
        return sum;
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
    public EquationList equations;
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        EquationList eqn = new EquationList(equation, result, equations);
        equations = eqn;
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
        int count = 0;
        EquationList temp = equations;
        while (count<n && temp != null){
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            count += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equations = equations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equations = null;
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
        EquationList temp = equations;
        while(temp != null){
            sum = this.add(sum, temp.result);
            temp = temp.next;
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
        int prod = 1;
        EquationList temp = equations;
        while (temp != null){
            prod = this.multiply(prod, temp.result);
            temp = temp.next;
        }
        return prod;
    }
}