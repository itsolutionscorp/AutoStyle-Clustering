def sum_of_squares(n):
    # x = 0
    # x += [i**2 for i in xrange(1, n+1)]
    # return x
    return sum([i**2 for i in xrange(1, n+1)])

def square_of_sum(n):
    x = sum([i for i in xrange(1, n+1)])**2
    return x

def difference(n):
    return square_of_sum(n)-sum_of_squares(n)
