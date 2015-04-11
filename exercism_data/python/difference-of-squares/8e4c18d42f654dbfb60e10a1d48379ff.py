def square_of_sum(n):
    return (n ** 2 + n) ** 2 / 4


def sum_of_squares(n):
    return sum([(i+1)**2 for i in xrange(n)])


def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
