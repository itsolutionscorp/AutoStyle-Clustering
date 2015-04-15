__author__ = 'djdick'

def square_of_sum(num):
    i = sum(range(1, num + 1))
    return i ** 2

def sum_of_squares(num):
    return sum(x**2 for x in range(1, num + 1))

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
