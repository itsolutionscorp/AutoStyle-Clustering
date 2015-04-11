def square_of_sum(n):
    return reduce(lambda x, y: x+y, [z for z in xrange(1, n+1)])**2

def sum_of_squares(n):
    l = [z for z in xrange(1, n+1)]
    l = map(lambda x: x**2, l)
    return reduce(lambda x, y: x+y, l)

def difference(n):
    return square_of_sum(n) - sum_of_squares(n)
