def square_of_sum(n):
    nsum = n * (n + 1) / 2
    return nsum ** 2


def sum_of_squares(n):
    return sum(i**2 for i in range(n+1))


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
