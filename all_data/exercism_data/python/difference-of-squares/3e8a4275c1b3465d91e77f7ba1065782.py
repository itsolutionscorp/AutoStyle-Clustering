__author__ = 'jeffmarkey'

def square_of_sum(num):
    sum = (num * (num + 1)) / 2
    sum = sum ** 2
    return sum


def sum_of_squares(num):
    sum = 0
    for x in range(1,num+1):
        sum = x**2 + sum
    return sum

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
