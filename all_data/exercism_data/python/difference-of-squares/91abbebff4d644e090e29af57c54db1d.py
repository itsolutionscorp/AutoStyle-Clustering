__author__ = 'jimblackler'


def sum_of_squares(n):
    return sum(i * i for i in xrange(1, n + 1))


def square_of_sum(n):
    return sum(xrange(1, n + 1)) ** 2


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
