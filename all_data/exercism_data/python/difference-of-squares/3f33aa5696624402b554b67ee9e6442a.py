__author__ = 'agupt15'


def square_of_sum(num):
    sum = 0
    for i in range(num + 1):
        sum += i
    return sum * sum


def sum_of_squares(num):
    sum = 0
    for i in range(num + 1):
        sum += i * i
    return sum


def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
