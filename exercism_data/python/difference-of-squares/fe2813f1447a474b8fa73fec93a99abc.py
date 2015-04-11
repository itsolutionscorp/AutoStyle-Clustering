def difference(n):
    return square_of_sum(n) - sum_of_squares(n)


def square_of_sum(n):
    return ((n + 1) * (n + 1) * n * n) // 4


def sum_of_squares(n):
    return ((n + 1) * (2 * n + 1) * n) // 6
