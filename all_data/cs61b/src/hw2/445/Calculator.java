import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList saved;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y){
            int a=x;
            int b=y;
            int rem=(a&b); //unless a and b are both 1, the remainders will be 0
            int add=(a^b); //this will invert the two numbers without accounting for history of remainders
        while ((rem!=0)){
            //find the remainder (if numbers need to be carried over)
            int carryover=rem<<1; //this will shift the remainders so that they will be carried over in the next addition
            rem=(add&carryover); //tells us if there are any remainders left. as long as there is a 1 (where rem + sum is 1), then must keep shifting.
            add=(add^carryover); //this will add the carried over remainders to the original sum
        }
        return add;
    }
        

    /*THISIS A TRIALLLL 
    public int add(int x, int y) {
        while (y!=0){
            int intermediate = (x&y);
            x=x^y;
            y=intermediate<<1;
        }
        return -1;
    } */

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
    int sum=0;
        while ((y!=0)){
            if ((y&1)==1){ //will only add the sum every two times. 
                sum=add(sum,x); //sum=2*sum. 
                //y=add(y,-1); //can't work for negative numbers
            }
            x=x<<1; //multiply x by 2 so that the sums run by 2^n
            y=y>>>1; //divides y by 2 so that the sums run by 2^n
            /*/else if ((x&1)==1){
                x=(x^1);
                x=x<<1; //multiplies x by 2
                y=y>>>1;*/
            }
        //}
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
    public void saveEquation(String equation, int result) {
        //EquationList saved = new EquationList(equation,result,null);

        if (saved==null){ //if there's no equations, then you want to define saved as something.
            saved = new EquationList(equation,result,null);
        }
        else 
            saved = new EquationList(equation,result,saved); //gets the other equations and results
        }


    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList s = saved;
        while (s!=null){
            System.out.println(s.equation + " = " + s.result);
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
        if (saved!=null){
        EquationList a = saved;
        while (n>0){
            System.out.println(a.equation + " = " + a.result);
            a=a.next;
            n=n-1;
        }
    }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        saved=saved.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        saved=null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    EquationList d=saved;
    int sum=0;
        if (d==null){
            return 0;
        }
        else{
            while (d!=null){
                sum=add(d.result, sum);
                d=d.next;
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
        EquationList p = saved;
        int product=1;
            if (p==null){
                return 1;
            } 
            else{
                while (p!=null){
                product=multiply(p.result,product);
                p=p.next;
            }
            return product;
            }
    }
}