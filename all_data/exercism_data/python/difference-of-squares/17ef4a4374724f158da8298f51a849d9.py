def sum_of_squares(n):
    sqsum = 0
    for n in xrange(1, n + 1):
        sqsum += n**2
    return sqsum


def square_of_sum(n):
    n_sum = 0
    for n in xrange(1, n + 1):
        n_sum += n
    return n_sum**2


def difference(n):
    return abs(sum_of_squares(n) - square_of_sum(n))
