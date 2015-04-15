def sum_to_n(n):
   """ Return sum of numbers from 1 .. n, ie 1 + 2 + 3 + ... + n.
       There is a well known formula for this so we may as well use it. 
   """
   return n * (n + 1) / 2.0

def square_of_sum(n):
   sum = sum_to_n(n)
   return sum * sum

def sum_of_squares(n):
   """ Return sum of squares of numbers from 1 .. n.  1*1 + 2*2 + + n*n
       There is a well known formula for this so we may as well use it. 
   """
   return  n * (n+1) * (2*n+1) / 6

def difference(n):
   return  square_of_sum(n) - sum_of_squares(n) 

