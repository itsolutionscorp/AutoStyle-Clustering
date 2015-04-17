import list.EquationList;
import java.util.Scanner;

public class Calculator {  

    EquationList listone = new EquationList("empty string", 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y   
     **/ 
    public int add(int x, int y) {
        int A = x; 
        int B = y;
        while (B != 0){
            A = (x ^ y);
            B = (x & y);
            B = (B << 1);
            x = A;
            y = B;
        }
        return A;
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
        int A = x;
        int B = y;
        int result= 0;
        while (B != 0){
            if ((B & 1) != 0){
                result = result + A;
            }
        A = A <<= 1;
        B = B >>>= 1;
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
        EquationList pointerA = listone; 
        if (listone.equation == "empty string") {
            listone = new EquationList(equation, result, null);
            pointerA = listone;
            return;
        } 
        while (pointerA.next != null){
            pointerA = pointerA.next;
            }
        pointerA.next = new EquationList(equation, result, null);
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList pointerB = listone;
        while (pointerB.next != null){
            System.out.println(pointerB.equation + " = " + pointerB.result);
            pointerB = pointerB.next;
        }
        System.out.println(pointerB.equation + " = " + pointerB.result);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList pointerC = listone;
        while (n != 0){
            pointerC = pointerC.next;
            n = n - 1;
        }
        System.out.println(pointerC.equation + " = " + pointerC.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList pointerD = listone;
        while (pointerD.next.next != null){
            pointerD = pointerD.next;
        }
        pointerD.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        listone.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointerE = listone;
        int Total = 0;
        while (pointerE.next != null){
            Total = Total + listone.result;
            pointerE = pointerE.next;
        }
        Total = pointerE.result + Total;
        return Total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList pointerF = listone;
        int Product = 1;
        while (pointerF.next != null){
            Product = Product * pointerF.result;
            pointerF = pointerF.next;
        }
        Product = pointerF.result * Product;
        return Product;
    }
}