def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))


def sum_of_squares(n):
    return sum(x**2 for x in range(1, n+1))


def square_of_sum(n):
    return sum(x for x in range(1, n+1))**2
