import list.EquationList;

public class Calculator {
  // YOU MAY WISH TO ADD SOME FIELDS

  EquationList savedEquation = null;
  /**
  * TASK 2: ADDING WITH BIT OPERATIONS
  * add() is a method which computes the sum of two integers x and y using
  * only bitwise operators.
  * @param x is an integer which is one of two addends
  * @param y is an integer which is the other of the two addends
  * @return the sum of x and y
  **/
  public int add(int x, int y) {
    if (y == 0){
      return x;
    }
    else{
      return add( x ^ y, (x & y) << 1);
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
    boolean isPositiveResult;
    int signX = x >> 31;
    int signY = y >> 31;

    if((signX == 0) && (signY == 0)){
      isPositiveResult = true;
    } else if((signX == -1) && (signY == -1)){
      isPositiveResult = true;
    } else{
      isPositiveResult = false;
    }


    if(signX == -1){
      x = negate(x);
    }
    if(signY == -1){
      y = negate(y);
    }
    int a = x;
    int b = y;
    int result = 0;
    Calculator newCalculator = new Calculator();
    while (b != 0)
    {
      if ((b & 01) != 0)
      {
        result = newCalculator.add(result, a);
      }
      a <<= 1;
      b >>= 1;
    }
    if(!isPositiveResult){
      result = negate(result);
    }
    return result;
  }

  public int negate(int x){
    Calculator newCalculator = new Calculator();
    x = newCalculator.add(~x, 1);
    return x;
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
    if(savedEquation == null){
      this.savedEquation = new EquationList(equation,result,null);
      }
    else{
      this.savedEquation = new EquationList(equation,result,savedEquation);
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
    EquationList temp = savedEquation;
    while(temp !=null){
      System.out.println(temp.equation + " = " + temp.result);
      temp = temp.next;
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
    EquationList temp = savedEquation;
    int i = 0;
    while(n >= i){
      System.out.println(temp.equation + " = " + temp.result);
      temp = temp.next;
      i+=1;
    }
  }

  /**
  * TASK 6: CLEAR AND UNDO
  * undoEquation() removes the most recent equation we saved to our history.
  **/
  public void undoEquation() {
    // YOUR CODE HERE
    savedEquation = savedEquation.next;
  }

  /**
  * TASK 6: CLEAR AND UNDO
  * clearHistory() removes all entries in our history.
  **/
  public void clearHistory() {
    savedEquation = null;
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
    EquationList temp = savedEquation;
    while(temp !=null){
      sum += temp.result;
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
    int product = 1;
    EquationList temp = savedEquation;
    while(temp !=null){
      product *= temp.result;
      temp = temp.next;
  }
  return product;
}
}
