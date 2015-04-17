def difference(n):
    return square_of_sum(n) - sum_of_squares(n)


def square_of_sum(n):
    return (n * (n + 1) / 2) ** 2


def sum_of_squares(n):
    return sum(i**2 for i in xrange(n + 1))