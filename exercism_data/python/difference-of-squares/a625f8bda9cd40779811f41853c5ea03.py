import math

def sum_of_squares(max):
    sum = 0
    for item in range(max+1):
        sum += item**2;
    return sum

def square_of_sum(max):
    sum = 0
    for item in range(max+1):
        sum += item;
    return sum**2

def difference(max):
    return square_of_sum(max)-sum_of_squares(max)
