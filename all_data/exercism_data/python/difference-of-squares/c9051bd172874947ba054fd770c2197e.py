"""
difference_of_squares - a module of sums and squares.
"""

def square_of_sum(n):
    """
    Square the sum of the range of natural numbers [1, n].
    """
    
    return sum(xrange(1, n + 1)) ** 2


def sum_of_squares(n):
    """
    Sum the squares of the range of natural numbers [1, n].
    """

    return sum(i ** 2 for i in xrange(1, n + 1))


def difference(n):
    """
    Find the difference between the square of the sum and the sum of the squares.
    """

    return square_of_sum(n) - sum_of_squares(n)
