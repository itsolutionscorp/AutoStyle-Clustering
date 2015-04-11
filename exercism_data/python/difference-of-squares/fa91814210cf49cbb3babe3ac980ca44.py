def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
    return reduce(lambda q,p: p+q, xrange(1, n + 1)) ** 2

def sum_of_squares(n):
    return reduce(lambda q,p: q+(p**2), xrange(1, n + 1))
