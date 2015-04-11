import math

def square_of_sum(integer):
    summation = 0
    for num in range(integer+1):
        summation+=num
    return summation**2

def sum_of_squares(integer):
    summation = 0
    for num in range(integer+1):
        summation+=num**2
    return summation

def difference(integer):
    return square_of_sum(integer) - sum_of_squares(integer)
