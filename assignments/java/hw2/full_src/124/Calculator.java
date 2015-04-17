import list.EquationList;

public class Calculator {
    EquationList history;
    EquationList reversedHistory;

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
      int carry = 0;
      int here = 1;

      int n;
      int m;

      for(int i = 0; i<Integer.SIZE;i++){
        n = x & here;
        m = y & here;

        sum = sum | n ^ m ^ carry; //XOR gate
        carry = ((n & m) | (n ^ m) & carry) << 1; //shifts carry and determines if carry is necessary

        here = here << 1;

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
      int product = 0;
      int cycle = y;
      int cycled = x;

      if(x < 0 && y > 0){
        cycle = y;
        cycled = x;
      } else if(y < 0 && x > 0){
        cycle = x;
        cycled = y;
      } else if(x < 0 && y < 0){
        int oldCycle = cycle;
        int oldCycled = cycled;
        cycle = 0;
        cycled = 0;
        for(int i = 0; i>oldCycle; i=add(-1, i)){
          cycle = add(cycle, 1);
        }
        for(int i = 0; i>oldCycled; i=add(-1, i)){
          cycled = add(cycled, 1);
        }

      }
      for (int i = 0; i<cycle; i++){
        product = add(product,cycled);
      }

      return product;
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
      if(history == null){
        history = new EquationList(equation, result, null);
      } else {
        history = addNode(history, equation, result);
      }
    }

    public EquationList addNode(EquationList E, String equation, int result){
      if(E == null){
        return new EquationList(equation, result, null);
      } return new EquationList(E.equation, E.result, addNode(E.next, equation, result));
    }


    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
      if(history != null){
        probe();
      }
    }

    private void probe(){
      EquationList E = reverser(history);
      while(E.next!=null){
        System.out.println(E.equation + " = " + E.result);
        E = E.next;
      }
      System.out.println(E.equation + " = " + E.result);
    }

    private EquationList reverser(EquationList E){
      while(E != null){
        reversedHistory = new EquationList(E.equation, E.result, reversedHistory);
        E = E.next;
      } return reversedHistory;
    }


    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
      if(history != null){
        probeNumbered(n);
      }
    }

    private void probeNumbered(int n){
      EquationList E = reverser(history);
      int i = 0;
      while(E.next!=null && i < n){
        System.out.println(E.equation + " = " + E.result);
        E = E.next;
        i += 1;
      }
      System.out.println(E.equation + " = " + E.result);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
      history = removeLast(history);
    }

    private EquationList removeLast(EquationList E){
      if(E.next.next == null){
        return new EquationList(E.equation, E.result, null);
      } return new EquationList(E.equation, E.result, removeLast(E.next));
    }




    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
      history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
      return sumCumulative(history);
    }

    private int sumCumulative(EquationList E){
      if(E == null){
        return 0;
      } return E.result + sumCumulative(E.next);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
      return productCumulative(history);
    }

    private int productCumulative(EquationList E){
      if(E == null){
        return 0;
      } return E.result * sumCumulative(E.next);
    }

}
