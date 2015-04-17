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
      int sum = 0;
      int i = 0;
      boolean carry = false;
      while (i < 32) {
        if (carry) {
          if(getBit(x, i) == 1 && getBit(y, i) == 1)
            sum = changeBit(sum, i);
          else if(getBit(x, i) == 0 && getBit(y, i) == 0) {
            sum = changeBit(sum, i);
            carry = false;
          }
        }
        else {
          if(getBit(x, i) == 1 && getBit(y, i) == 1)
            carry = true;
          else if(getBit(x, i) != getBit(y, i))
            sum = changeBit(sum, i);
        }
        i += 1;
      }
      return sum;
    }

    // Return the bit value at the specified offset (zero-indexed).
    private int getBit(int number, int offset) {
      return (number & ( 1 << offset )) >> offset;
    }

    // Flip the bit value at the specified offset (zero-indexed).
    private int changeBit(int number, int offset) {
      return (number ^ (1 << offset));
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
      int product = 0;
      boolean negative = false;

      if (x < 0) {
        negative = true;
        x = flipSign(x);
      }

      if (y < 0) {
        if (negative)
          negative = false;
        else
          negative = true;
        y = flipSign(y);
      }

      while (y > 0) {
        product = add(product, x);
        y -= 1;
      }

      if (negative)
        product = flipSign(product);

      return product;
    }

    // Flips the sign of the given number using bit operations.
    private int flipSign(int number) {
      return add(1, (~ number));
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
      head = new EquationList(equation, result, head);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
      EquationList pointer = head;

      while (pointer != null) {
        System.out.println(pointer.equation + " = " + pointer.result);
        pointer = pointer.next;
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
      EquationList pointer = head;

      int count = 1;
      while (count < n) {
        pointer = pointer.next;
        count += 1;
      }
      System.out.println(pointer.equation + " = " + pointer.result);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
      if (head != null)
        head = head.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
      head = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
      int sum = 0;
      EquationList pointer = head;

      while (pointer != null) {
        sum += pointer.result;
        pointer = pointer.next;
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
      int product = 1;
      EquationList pointer = head;

      while (pointer != null) {
        product *= pointer.result;
        pointer = pointer.next;
      }
      return product;
    }
}
