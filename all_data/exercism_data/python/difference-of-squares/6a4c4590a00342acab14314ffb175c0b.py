def difference(n):
    return abs(square_of_sum(n) - sum_of_squares(n, 0))


def square_of_sum(n):
    if not n % 2:  # even
        return (n / 2 * (n + 1)) ** 2
    return ((n / 2 * (n + 1)) + ((n + 1) / 2)) ** 2


def sum_of_squares(n, total=0):
    """
    Python-y.

    :rtype : int
    :param n:
    :param total:
    :return:
    """
    while True:
        if not n:
            return total
        n, total = n-1, total + (n ** 2)

print sum_of_squares(5)
