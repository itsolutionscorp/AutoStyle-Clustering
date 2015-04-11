"""Sum square difference."""


def sum_of_squares(n):
    """Return the sum of the squares of the first ``n`` natural numbers."""
    # 1 ** 2 + 2 ** 2 + ... + n ** 2 = n * (n + 1) * (2 * n + 1) / 6
    return n * (n + 1) / 2 * (2 * n + 1) / 3
    # return sum(x ** 2 for x in xrange(1, n + 1))


def square_of_sum(n):
    """Return the square of the sum of the first ``n`` natural numbers."""
    # 1 + 2 + ... + n = n * (n + 1) / 2
    return (n * (n + 1) / 2) ** 2
    # return sum(xrange(1, n + 1)) ** 2


def difference(n):
    """Return square_of_sum(n) - sum_of_squares(n)."""
    return square_of_sum(n) - sum_of_squares(n)
