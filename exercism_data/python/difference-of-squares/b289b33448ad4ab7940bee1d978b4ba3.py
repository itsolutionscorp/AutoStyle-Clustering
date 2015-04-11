def square_of_sum(n):
    return sum(range(1, n+1))**2


def sum_of_squares(n):
    return sum(map(lambda x: x*x, range(1, n+1)))


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
