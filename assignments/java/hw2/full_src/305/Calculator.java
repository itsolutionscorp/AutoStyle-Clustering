import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null;
    EquationList ptr = history;
    //EquationList empty = null;
    int total_equations = 0;


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    private int getBit(int x, int i)
        {
            int raw_shifted_num = x >>> i;  //Move to right to put desired index value as last number
            int index_bit = raw_shifted_num & 1; // 1 & numb1  // clears out everything to the left of the desired number and returns its value
            return index_bit; 

        }

    private int setBit(int x, int i)
        {
          //int helper_number =  1;
          int helper_number = 1 << i; 
          return x | helper_number;

        }

    public int add(int x, int y) {
        // YOUR CODE HERE
        int secret_carrying_number = 0;
        int final_num = 0;

        for(int i = 0; i <= 31; i++)
        {
            int curr_x = getBit(x, i);
            int curr_y = getBit(y, i);
            int curr_carry = getBit(secret_carrying_number, i);

            int curr_sum = curr_x ^ curr_y; // adding 1 and 0 = 1, 1 + 1 = 0, 0+ 0 = 0

            if(curr_sum == 1) //1 and 0 
            {
                if(curr_carry == 1) 
                {
                    secret_carrying_number = setBit(secret_carrying_number, i+1);
                }
                else
                {
                    //curr_carry must be 0
                    final_num = setBit(final_num, i);
                }
            }
            

            else if (curr_sum == 0) //1 + 1 = 0 or 0+ 0 = 0
            {
                if(curr_carry == 1) 
                {
                    final_num = setBit(final_num, i);
                    if( curr_x == 1) // 1c, 1, 1
                    {
                        secret_carrying_number = setBit(secret_carrying_number, i+1);
                    }
                    else
                    {
                        // 1c, 0, 0 
                    }

                }
                else //curr_carry is 0 
                {
                    if(curr_x == 1) // 0c, 1, 1
                    {
                        secret_carrying_number = setBit(secret_carrying_number, i+1);
                    }
                    else // 0c, 0, 0
                    {
                        // all 0's
                    }

                }
            }

        }
        return final_num;

        
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


        //ONLY WORKS WHEN Y IS A POWER OF 2
        // int y_copy = y;
        // int i = 1;
        // while(y_copy != 0)
        // {
        //     y_copy = y_copy >> 1;
        //     if(y_copy == 1 ) //y is a power of 2
        //     {
        //         return x << i;
        //     }
        //     i++;
        // }
        //  //y not a power of 2
        // return 123;


        //NEED TO FIGURE OUT HOW TO ADD THE REMAINING X'S AFTER 2^i IS CALCULATED
        // int y_copy = y;
        // int i = 1; //alows you to figure out the power of 2
        // while(y_copy != 0)
        // {
        //     y_copy = y_copy >> 1; //divides by 2

        //     if(y_copy == 1) //divided by two as many times as possible
        //     {
        //         if((y_copy & (add(y_copy, -1)) == 0) // y_copy is a power of 2 DONT USE **-**!!!
        //         {
        //             int final_xs_to_add = add(y, -y_copy);
        //             return add(x << i, 3 * final_xs_to_add );   
        //         }
        //         else //y isn't a power of two- we need to find the closest number that it
        //         {
        //             y_copy = y_copy -1;
        //             i = 0;
        //         }
        //     }

        //     i++;
        // }


        //try recursive ---CAN'T KEEP TRACK OF WHAT POWERS of 2 y is
        // if(y == 0)
        // {
        //     return 0;
        // }
        // else if(y == 1)
        // {
        //     return x;
        // }   
        // else if((y & (y - 1)) == 0) //power of 2
        // {
        //     return x << i;
        // }

        //MOST RECENT- NOT WORKING
        // Subtracts 1 from y_copy until nearest power of 2 is found
        // then adds back the correct number of x's correlated to (y-y_copy)
        // int y_copy = y;
        // int i = 1; //allows you to figure out the highest power of 2
        // while(y_copy != 0)
        // {
        //     y_copy = y_copy >> 1; //divides by 2^1

        //     if(y_copy == 1) //divides by two as many times as possible
        //     {
        //         if((y_copy & (y_copy -1)) == 0) // y_copy is a power of 2 DONT USE **-**!!!
        //         {
        //             int final_xs_to_add = y -y_copy;
        //             int final_num_to_add = 0;
        //             while(final_xs_to_add > 0)
        //             {
        //                 final_num_to_add = add(x, final_xs_to_add);
        //                 final_xs_to_add = final_xs_to_add - 1;

        //             }
        //             return add(x << i, final_num_to_add );   
        //         }
        //         else //y isn't a power of two- we need to find the closest number that is
        //         {
        //             y_copy = y_copy -1;
        //             i = 0;
        //         }
        //     }

        //     i++;
        // }
        // return 10001;

        //***implementing subtract -n = ~n +1***//

        // FINDS POWERS OF 2
        // int i = 0;
        // while(!((y & (y - 1)) == 0)) //not a power of 2
        // {
        //     i++
        // }

        int final_num = 0;
        for(int i = 0; i < 32; i++)
        {
            if(getBit(y, i) == 1)
            {
                final_num = add(final_num, x << i);
            }
        }
        return final_num;



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
        // YOUR CODE HERE
        history = new EquationList(equation, result, history);
        total_equations += 1;


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
        printHistory(total_equations);

    
        // while(history != null)
        // {
        //     System.out.println(ptr.equation)
        //     ptr = ptr.next;
        // }
        // ptr = history;

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
        //System.out.println("hi" + n);
        ptr = history;
        if(n > total_equations)
        {
            n = total_equations;
        }

        while(n >= 1)
        {

            System.out.println(ptr.equation + " = " + ptr.result);
            ptr = ptr.next;
            n = n - 1;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
        total_equations = total_equations - 1;




    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
         
        history = null;
        total_equations = 0;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int total_sum = 0;
        ptr = history;
        while(ptr != null)
        {
            total_sum += ptr.result;
            ptr = ptr.next;
        }
        return total_sum;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int total_product = 1;
        ptr = history;
        while(ptr != null)
        {
            total_product *= ptr.result;
            ptr = ptr.next;
        }
        return total_product;
    }
}