def sum_of_squares(n):
    if n >= 1:
        return n ** 2 + sum_of_squares(n - 1)
    else:
        return 0


def square_of_sum(n):
    return _triangle(n) ** 2


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)


def _triangle(n):
    if n >= 1:
        return n + _triangle(n - 1)
    else:
        return 0
