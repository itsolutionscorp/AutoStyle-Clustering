def difference(n):
    return abs(square_of_sum(n) - sum_of_squares(n))


def square_of_sum(n):
    return sum(range(1, n + 1)) ** 2


def sum_of_squares(n):
    return sum(i * i for i in range(1, n + 1))