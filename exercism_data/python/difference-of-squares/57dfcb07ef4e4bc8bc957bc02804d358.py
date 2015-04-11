#!/usr/bin/python
# exercism python 8: dfference of squares

def square_of_sum(bound):
    return sum(xrange(1, bound+1))**2
    
def sum_of_squares(bound):
    return sum((x**2 for x in xrange(1,bound+1)))
    
def difference(bound):
    return square_of_sum(bound) - sum_of_squares(bound)
