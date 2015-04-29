import math

def square_of_sum(integer):
    summation = sum(num for num in range(integer+1))
    return summation**2

def sum_of_squares(integer):
    summation = sum(num**2 for num in range(integer+1))
    return summation

def difference(integer):
    return square_of_sum(integer) - sum_of_squares(integer)
