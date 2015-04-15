import math

def difference(n):
    return math.fabs(square_of_sum(n)-sum_of_squares(n))
    
def square_of_sum(n):
    return sum(range(1,n+1))**2

def sum_of_squares(n):
    return sum(x**2 for x in range(1,n+1))
