def difference(n):
    return abs(square_of_sum(n) - sum_of_squares(n))


def square_of_sum(n):
    return sum(range(1, n+1)) ** 2


def sum_of_squares(n):
    return reduce(lambda m, x: x ** 2 + m, xrange(1, n+1), 0)
