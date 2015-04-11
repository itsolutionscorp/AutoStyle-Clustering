#William Morris
#exercism.io
#difference_of_squares.py

import math

#helper function
def _of_sum(n):
    if n == 0:
        return 0
    else:
        return n + _of_sum(n-1)

def square_of_sum(n):
    return _of_sum(n)**2

def sum_of_squares(n):
    if n == 0:
        return 0
    else:
        return n**2 + sum_of_squares(n-1)

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

    
