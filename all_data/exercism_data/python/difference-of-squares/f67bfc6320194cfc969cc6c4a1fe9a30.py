'''This library contains functions for find the sum of the first n squares, the
square of the sum of the first n numbers, and the difference between those.'''

def square_of_sum(number):
    '''This function will return the square of the sum of natural numbers up to
    and including the variable number.'''

    return sum([_c for _c in range(1, number + 1)]) ** 2


def sum_of_squares(number):
    '''This function will return the sum of the squares of the natural numbers
    up to and including the variable number.'''

    return sum([_c ** 2 for _c in range(1, number + 1)])


def difference(number):
    '''This function will return the difference between the results returned by
    the square_of_sum function and sum_of_squares function.'''

    return square_of_sum(number) - sum_of_squares(number)
