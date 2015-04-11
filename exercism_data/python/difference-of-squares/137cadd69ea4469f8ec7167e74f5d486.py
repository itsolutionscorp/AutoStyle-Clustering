from numpy import arange

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    return sum(range(n+1))**2

def sum_of_squares(n):
    return sum(arange(n+1)**2)
