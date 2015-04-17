import list.EquationList;

public class Calculator {
  private EquationList history;
  private int historyLength = 0;

  /**
   * Returns an int as a 32-bit bitstring.
   */
  public static String bitstr(int x) {
    return String.format("%32s", Integer.toBinaryString(x)).replace(" ", "0");
  }

  public static void printBits(int x) {
    System.out.println(bitstr(x));
  }

  /**
   * Increments an int using only bitwise operators.
   */
  public int increment(int x) {
    if ((x & 1) == 0) {
      return x | 1;
    }
    return increment(x >>> 1) << 1;
  }

  /**
   * Decrements an int using only bitwise operators.
   */
  public int decrement(int x) {
    return add(x, ~0);
  }

  /**
   * add() is a method which computes the sum of two integers x and y using
   * only bitwise operators.
   * @param x is an integer which is one of two addends
   * @param y is an integer which is the other of the two addends
   * @return the sum of x and y
   **/
  public int add(int x, int y) {
    return add(x, y, 0, 0);
  }

  private int add(int x, int y, int carry, int bit) {
    if (bit >= Integer.SIZE) {
      return 0;
    }

    int xbit = x & (1 << bit);
    int ybit = y & (1 << bit);
    int next_carry = ((xbit & ybit) | (xbit & carry) | (ybit & carry)) << 1;

    return (xbit ^ ybit ^ carry) | add(x, y, next_carry, increment(bit));
  }

  /**
   * Returns -x using only bitwise operators.
   */
  public int negate(int x) {
    return increment(~x);
  }

  /**
   * Returns x - y using only bitwise operators.
   */
  public int subtract(int x, int y) {
    return add(x, negate(y));
  }

  /**
   * multiply() is a method which computes the product of two integers x and
   * y using only bitwise operators.
   * @param x is an integer which is one of the two numbers to multiply
   * @param y is an integer which is the other of the two numbers to multiply
   * @return the product of x and y
   **/
  public int multiply(int x, int y) {
    return multiply(x, y, 0, 0);
  }

  private int multiply(int x, int y, int partial, int bit) {
    if (bit >= Integer.SIZE) {
      return partial;
    }

    int xbit = (x >> bit) & 1;

    if (xbit == 0) {
      return multiply(x, y, partial, increment(bit));
    } else {
      return multiply(x, y, add(partial, y << bit), increment(bit));
    }
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
    EquationList newEntry = new EquationList(equation, result, null);

    if (history == null) {
      history = newEntry;
    } else {
      newEntry.next = history;
      history = newEntry;
    }

    historyLength = increment(historyLength);
  }

  /**
   * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
   * printAllHistory() prints each equation (and its corresponding result),
   * most recent equation first with one equation per line.  Please print in
   * the following format:
   * Ex   "1 + 2 = 3"
   **/
  public void printAllHistory() {
    printHistory(historyLength);
  }

  /**
   * printHistory() prints each equation (and its corresponding result),
   * most recent equation first with one equation per line.  A maximum of n
   * equations should be printed out.  Please print in the following format:
   * Ex   "1 + 2 = 3"
   **/
  public void printHistory(int n) {
    EquationList entry = history;
    n = Math.min(n, historyLength);

    while (entry != null && n != 0) {
      System.out.println(entry.equation + " = " + entry.result);
      entry = entry.next;
      n = decrement(n);
    }
  }

  /**
   * TASK 6: CLEAR AND UNDO
   * undoEquation() removes the most recent equation we saved to our history.
   **/
  public void undoEquation() {
    if (history != null) {
      history = history.next;
      historyLength = decrement(historyLength);
    }
  }

  /**
   * TASK 6: CLEAR AND UNDO
   * clearHistory() removes all entries in our history.
   **/
  public void clearHistory() {
    history = null;
    historyLength = 0;
  }

  /**
   * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
   * cumulativeSum() computes the sum over the result of each equation in our
   * history.
   * @return the sum of all of the results in history
   **/
  public int cumulativeSum() {
    int sum = 0;
    EquationList entry = history;
    while (entry != null) {
      sum = add(sum, entry.result);
      entry = entry.next;
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
    EquationList entry = history;
    while (entry != null) {
      product = multiply(product, entry.result);
      entry = entry.next;
    }
    return product;
  }
}
