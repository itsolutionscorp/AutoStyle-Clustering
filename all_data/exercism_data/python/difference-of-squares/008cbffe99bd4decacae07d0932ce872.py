def square_of_sum(n):
    """
    Returns the square of the sum for serie (1 + 2 + ... + n)
    Uses Faulhaber's formula for sum of squares:
      http://en.wikipedia.org/wiki/Faulhaber%27s_formula
    """
    sums = n * (n+1) / 2
    return sums**2


def sum_of_squares(n):
    """
    Returns the sum for serie (1**2 + 2**2 + .. + n**2)
    Uses Faulhaber's formula for sum:
      http://en.wikipedia.org/wiki/Faulhaber%27s_formula
    """
    return n * (n+1) * (2*n+1)/6


def difference(n):
    return square_of_sum(n)-sum_of_squares(n)
