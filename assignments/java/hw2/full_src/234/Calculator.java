import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eqList = new EquationList("", 0, null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
       public int add(int x, int y) {
        //z an integer holding the numbers that carry.
        //result self-explanatory.
        if (x > y) {
            return add(y, x);
        }
          
          if (x == 0) {
             return y;
          }
        if (x == y) {
            return (x << 1);
        }

        if ((x | y) != y && x == 1) {
            return x | y;
        }

        if ((x | y) == y && x == 1) {
            while (x < y) {
                x = x << 1;
                if ((x | y) != y) {
                    int z = x;
                    while (x != 1) {
                        x = x >> 1;
                        y = y >> 1;
                    }
                    y = x | y;
                    while (x != z) {
                        x = x << 1;
                        y = y << 1;
                    }
                    return y;
                }
            }
            return x;
        }

        if ((x | y) != y && (x & y) == 0) {
            return x | y;
        }

        if (x < 0) {
            if (y < 0) {
                int result = add(add(~x, 1), add(~y, 1));
                return add(~result, 1);
                //return add(~add(add(~x, 1), add(~y, 1)), 1);
            }
            return negative(x, y);
        }

        if (y < 0) {
            return negative(y, x);
        }
    int result = 0;
    int carry = 0;
    int max = 1;
    int index = 0;
    int a = 0;
    int b = 0;
    int c = 0;
          while (max < y) {
            a = getBit(x, index);
            b = getBit(y, index);
            c = getBit(carry, index);
             if ((a == 0 && b == 0) && c == 0) {
                index += 1;
                max = max << 1;
             }
             else if ((((a > b) || (b > a)) && c == 0) || ((a == 0 && b == 0) && c == 1)) {
                carry = changeBit(carry, index);
                result = changeBit(result, index);
                index += 1;
                max = max << 1;
             }
             else if ((((a > b) || (b > a)) && c == 1) || (((a == 1) && (b == 1)) && c == 0)) {
                index += 1;
                carry = changeBit(carry, index);
                max = max << 1;
    }
                      else {
                         result = changeBit(result, index);
                         //carry = changeBit(carry, index);
                         index += 1;
                         carry = changeBit(carry, index);
                         max = max << 1;
          }
          }
          c = getBit(carry, index); //
                if (c == 1) {
                  result = changeBit(result, index);
                }
    return result;
       }

    private int negative(int x, int y) {
        int help = 0;
        while (help < y) {
            help = add(help, 1);
            x += 1;
        }
        return x;
    }
    private int getBit(int a, int ind) {
        while (ind > 0) {
            a = a >> 1;
            ind -= 1;
        }
        if ((a & 1) == 1) {
            return 1;
        }
        return 0;
    }

    private int changeBit(int x, int i) {
        int check = 1;
        int ch_i = i;
        while (ch_i > 0) {
            check = check << 1;
            ch_i -= 1;
        }

        if (check > x) {
            return add(check, x);
        }
        if (x == 0) {
            if (i == 0) {
                return 1;
            }
            int result = 1;
            while (i > 0) {
                result = result << 1;
                i -= 1;
            }
            return result;
        }
        if (i == 0) {
            if (getBit(x, 0) == 0) {
                return add(1, x);
            }
            x = x >> 1;
            x = x << 1;
            return x;
        }
        int y = 0;
        if (getBit(x, 0) == 1) {
            y = 1;
        }
        int index = 1;
        while (index < i) {
            if (getBit(x, index) == 1) {
                y = y << 1;
                y = add(1, y);
            } else {
                y = y << 1;
            }
           index += 1;
        }
        y = y << 1;
        if (getBit(x, i) == 0) {
            y = add(1, y);
        }
        return x | y;
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
        if (x > y) {
            return multiply(y, x);
        }

        if ((x < 0) && (y < 0)) {
            return multiply(add(~x, 1), add(~y, 1));
        }

        if (x == 0 || y == 0) {
            return 0;
        }
        if (x == 1) {
            return y;
        }

        if (x % 2 == 0) {
            return multiply(x >> 1, y << 1);
        }

        int on = 0;
        if (x < 0) {
            on = 1;
            x = add(~x, 1);
        }

        if (y < 0) {
            on = 1;
            y = add(~y, 1);
        }
        int num = y;
        while (x > 1) {
            y = add(num, y);
            x -= 1;
        }
        if (on == 0) {
        return y;
}
    return (add(~y, 1));
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
        EquationList temp = new EquationList(equation, result, eqList);
        eqList = temp;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList iter = eqList;
        while (iter.next != null) {
            System.out.println(iter.equation + " = " + iter.result);
            iter = iter.next;
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
        EquationList iter2 = eqList;
        while ((iter2.next != null) || (n > 0)) {
            System.out.println(iter2.equation + " = " + iter2.result);
            iter2 = iter2.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqList = eqList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = new EquationList("", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList iter3 = eqList;
        int sum = 0;
        while (iter3.next != null) {
            sum += iter3.result;
            iter3 = iter3.next;
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
        EquationList iter4 = eqList;
        int product = 1;
        while (iter4.next != null) {
            product *= iter4.result;
            iter4 = iter4.next;
        }
        return product;
    }
}