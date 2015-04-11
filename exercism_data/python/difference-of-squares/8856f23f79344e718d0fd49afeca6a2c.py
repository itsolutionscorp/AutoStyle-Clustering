__author__ = 'Micha'


def square_of_sum(number):
    sum = 0
    for i in range(number + 1):
        sum += i
    return sum**2

def sum_of_squares(number):
    sum = 0
    for i in range(number + 1):
        sum += i**2
    return sum

def difference(number):
    return square_of_sum(number) - sum_of_squares(number)
