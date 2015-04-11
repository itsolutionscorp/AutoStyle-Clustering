"""
Solves the problem of finding the difference between the
square of sums up to a value and the sum of all squares
up to that same value. Project Euler #6
"""


def square_of_sum(value):
    """
    Sums all values from 1 to value and squares it
    """
    total = int()
    for i in range(value + 1):
        total += i
    return total ** 2


def sum_of_squares(value):
    """
    Squares all values from 1 to value and sums them
    """
    total = int()
    for i in range(value + 1):
        total += i ** 2
    return total


def difference(value):
    """
    Subtracts (the sum of all squares from 1 to value)
    from (the square of the sum from 1 to value).
    """
    return square_of_sum(value) - sum_of_squares(value)
