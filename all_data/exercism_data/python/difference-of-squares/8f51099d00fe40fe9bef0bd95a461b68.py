import math

def sum_of_squares(num):
    return (num*(num+1)*(2*num+1))/6

def square_of_sum(num):
    return ((num*(num+1))/2)**2


def difference(max):
    return square_of_sum(max)-sum_of_squares(max)
