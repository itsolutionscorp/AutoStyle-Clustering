def sum_of_squares(n):
    return (n * (n + 1) * (2 * n + 1)) / 6


def square_of_sum(n):
    return (n * (n + 1) / 2) ** 2


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
