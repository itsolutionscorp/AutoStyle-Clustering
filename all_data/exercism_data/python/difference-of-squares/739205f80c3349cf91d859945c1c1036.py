def difference(r):
    return abs(square_of_sum(r) - sum_of_squares(r))


def square_of_sum(r):
    s = sum(xrange(1, r+1))
    return s**2


def sum_of_squares(r):
    return sum([s**2 for s in xrange(1, r+1)])
