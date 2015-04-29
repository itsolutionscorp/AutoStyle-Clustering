def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))


def square_of_sum(n):
    # use sum([1 ... n]) = (n + 1)(n) / 2
    return ((n + 1) * (n / 2)) ** 2


def sum_of_squares(n):
    return sum(x ** 2 for x in range(1, n + 1))
