from __future__ import division

def square_of_sum(n):
    return (n*(n+1))**2 // 4

def sum_of_squares(n):
    return (2*n**3 + 3*n*n + n) // 6

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
