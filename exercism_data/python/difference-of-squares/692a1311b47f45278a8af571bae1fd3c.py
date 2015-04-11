def square_of_sum(n):
    value = 0
    for x in range(1, n + 1):
        value += x
    return value ** 2


def sum_of_squares(n):
    value = 0
    for x in range(1, n + 1):
        value += x ** 2
    return value


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
