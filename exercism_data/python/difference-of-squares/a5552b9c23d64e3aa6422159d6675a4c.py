__author__ = 'Dalton'

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)

def sum_of_squares(num):
    x = 0
    for i in range(num+1):
        x += i**2
    return x

def square_of_sum(num):
    x = 0
    for i in range(num+1):
        x += i
    return x**2
