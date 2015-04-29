import list.EquationList;
public class Calculator {
// YOU MAY WISH TO ADD SOME FIELDS
EquationList history;
/**
* TASK 2: ADDING WITH BIT OPERATIONS
* add() is a method which computes the sum of two integers x and y using
* only bitwise operators.
* @param x is an integer which is one of two addends
* @param y is an integer which is the other of the two addends
* @return the sum of x and y
**/
public int add(int x, int y) {
while ((x & y) != 0) {
int temp = x;
x = x & y;
x = x << 1;
y = temp ^ y;
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
int increment = x;
int stored = 0;
boolean multiplyneg = false;
if((x < 0) && (y < 0)){
x = this.add(~x, 1);
y = this.add(~y, 1);
}
else if(y < 0){
multiplyneg = true;
y = this.add(~y, 1);
}
if((x == 0) || (y == 0)){
return 0;
}
while(y > 1){
if((y & 1) == 1){
stored = this.add(stored, x);
}
x = x << 1;
y = y >>> 1;
}
x = this.add(x, stored);
if(multiplyneg){
x = ~this.add(x, -1);
}
return x;
}
/**
* TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
* saveEquation() updates calculator history by storing the equation and
* the corresponding result.
* Note: You only need to save equations, not other commands. See spec for
* details.
* @param equation is a String representation of the equation, ex. "1 + 2"
* @param result is an integer corresponding to the result of the equation
**/
public void saveEquation(String equation, int result) {
if(history == null){
history = new EquationList(equation, result, null);
}
else{
history = new EquationList(equation, result, history);
}
}
/**
* TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
* printAllHistory() prints each equation (and its corresponding result),
* most recent equation first with one equation per line. Please print in
* the following format:
* Ex "1 + 2 = 3"
**/
public void printAllHistory() {
EquationList list = history;
while(list != null){
System.out.println(list.equation + " = " + list.result);
list = list.next;
}
}
/**
* TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
* printHistory() prints each equation (and its corresponding result),
* most recent equation first with one equation per line. A maximum of n
* equations should be printed out. Please print in the following format:
* Ex "1 + 2 = 3"
**/
public void printHistory(int n) {
EquationList list = history;
int count = 0;
while(count < n){
System.out.println(list.equation + " = " + list.result);
list = list.next;
count ++;
}
}
// public void printHistory(int n) {
// EquationList list = history;
// int count = 0;
// String str = "";
// while(count < n){
// s = "" + str + list.equation + " = " + list.result + "\n";
// list = list.next;
// count ++;
// }
// return str;
// }
/**
* TASK 6: CLEAR AND UNDO
* undoEquation() removes the most recent equation we saved to our history.
**/
public void undoEquation() {
history = history.next;
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
EquationList list = history;
int sum = 0;
<<<<<<< HEAD
while(list != null){
=======
while(list.next != null){
>>>>>>> master
sum += list.result;
list = list.next;
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
EquationList list = history;
<<<<<<< HEAD
int prod = 1;
while(list != null){
=======
int prod = 0;
while(list.next != null){
>>>>>>> master
prod = prod * list.result;
list = list.next;
}
return prod;
}
}
