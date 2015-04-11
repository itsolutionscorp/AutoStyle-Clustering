from numpy import arange, sum

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    numbers = arange(1, n+1)
    return sum(numbers)**2

def sum_of_squares(n):
    numbers = arange(1, n+1)
    return sum(numbers**2)
