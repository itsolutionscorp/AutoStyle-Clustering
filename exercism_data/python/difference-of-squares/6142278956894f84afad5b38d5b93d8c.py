__author__ = 'djdick'

def square_of_sum(num):
    sum = 0
    for i in range(1,num + 1):
        sum += i
    return sum ** 2

def sum_of_squares(num):
    sum = 0
    for i in range(1, num + 1):
        sum += i ** 2
    return sum

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
