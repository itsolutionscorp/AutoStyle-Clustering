import math

def sum_of_squares(num):
    return sum(map(lambda x: x**2, range(1, num+1)))


def square_of_sum(num):
    return pow(sum(range(1, num+1)), 2)


def difference(max):
    return square_of_sum(max)-sum_of_squares(max)
