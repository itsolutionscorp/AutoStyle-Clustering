import list.EquationList;

public class Calculator {
    
    private EquationList head;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) { 

        int and = x & y; //serves to measure carry
        int xor = x ^ y;

        //keep running loop until there are no more carries
        while (and != 0) { 
            and = and << 1; //move carry over left
            int temp = xor ^ and;
            and = and & xor; //reset carry
            xor = temp; //addition thus far
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
        //could have ran add function on x and x y times
        //which would be more concise
        //but can not also use a nontrivial bitwise operation
        //at the same time

        int result = 0;

        if(x == 0)
        {
            return 0;
        }
        
        //keep going till y = 0, 
        //since any number times 0 is 0
        while (y != 0) 
        {
            if ((y & 1) != 0)
            {
                result = add(result, x); 
            }

            //since 2 times any number 
            //is just the binary representation
            //left shifted
            x = x << 1; //mult x by 2
            y = y >>> 1; //divide y by 2
            //and division will eventually take floor 1 >> 1 == 0
        }

        //if y = 0, then it will never enter loop and
        //return 0 as it should
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
        if(head == null)
        {
            head = new EquationList(equation, result, null);
        }
        else
        {
            EquationList p = head;
            while(p.next != null)
            {
                p = p.next;
            }
            p.next = new EquationList(equation, result, null);
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
        EquationList p = head;
        int length = 0;
        while(p !=  null)
        {
            length++;
            p = p.next;
        }
        printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList p = head;
        int length = 0;
        while(p !=  null)
        {
            length++;
            p = p.next;
        }
        
        int l = length - n;
        
        EquationList j = head;
        EquationList most_recent = null;
        int i = 0;
        while(j != null)
        {
            if(i >= l)
            {
                if(most_recent == null)
                {
                    most_recent = new EquationList(j.equation, j.result, null);
                }
                else
                {
                    most_recent = new EquationList(j.equation, j.result, most_recent);
                }
            }
            j = j.next;
            i++;
        }

        EquationList k = most_recent;
        while(k != null)
        {
            System.out.print(k.equation + " = ");
            System.out.println(k.result);
            k = k.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList p = head;
        while(p.next.next != null)
        {
            p = p.next;
        }
        p.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        //although this leaves rogue linked lists 
        //to be in the program
        //it works
        head = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if(head == null)
        {
            return 0;
        }
        else
        {
            int result = 0;
            EquationList p = head;
            while(p != null)
            {
                result += p.result;
                p = p.next;
            }
            return result;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if(head == null)
        {
            return 1;
        }
        else
        {
            int result = 1;
            EquationList p = head;
            while(p != null)
            {
                result *= p.result;
                p = p.next;
            }
            return result;
        }
    }
}