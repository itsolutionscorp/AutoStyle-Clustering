# Difference_of_squares


def square_of_sum(n):
    ssum = 0
    for i in range(1, n+1):
        ssum += i
    return ssum**2


def sum_of_squares(n):
    sums = 0
    for i in range(1, n+1):
        sums += i**2
    return sums


def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))
