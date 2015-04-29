import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList calcList;
    int size = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    private int getBit(int number, int b) {
      return (number >> b) & 1;
    }

    private int setBit(int number, int b) {
      return  number | (1 << b);
    }

    public int add(int x, int y) {
      /* ATTEMPT 2: AFTER LOTS OF PIAZZA */
      int result = x;
      int carry = 0; // We need to add the carries.
      while(y != 0) {
        carry = result & y;
        result = result ^ y;
        y = carry << 1;
      }
      return result;
      /* ATTEMPT 1: ONLY WORKS WITH POSITIVE NUMBERS***/
      /*  int result = 0;
        boolean carry = false;
        int i = 1;
        while(true) {
          int maskX = x & i;
          int maskY = y & i;
          int xor = maskX ^ maskY;
          if(carry) {
            xor = xor ^ i;
          }
          carry = false;
          if(maskX != 0 && xor == 0) {
            carry = true;
          }
          result = xor | result;
          if(i == -2147483648)
            break;
          i = i << 1;
        }
        return result;
       */ 
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
      // You could represent any number with powers of 2!
      int bit = 0;
      int result = 0;
      while(bit < 32) {
        int bitval = getBit(y, bit);
        if(bitval == 1) {
          result = add(result, x << bit);
        }
        bit++;
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
      calcList = new EquationList(equation, result, calcList);
      size++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
      EquationList current = calcList;
      if(current == null)
        return;
      while(current != null && n > 0) {
        System.out.println(current.equation +  " " + "= " + Integer.toString(current.result));
        current = current.next;
        n--;
      }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
      if(calcList == null) {
        return;
      } 
      calcList = calcList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
      calcList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
      if(calcList == null) {
        return 0; 
      }
      int sum = 0;
      EquationList current = calcList;
      while(current != null) {
        sum = sum + current.result;
        current = current.next;
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
      if(calcList == null) {
        return 1;
      }
      int product = 1;
      EquationList current = calcList;
      while(current != null) {
        product = product * current.result;
        current = current.next;
      }
      return product;
    }
}
