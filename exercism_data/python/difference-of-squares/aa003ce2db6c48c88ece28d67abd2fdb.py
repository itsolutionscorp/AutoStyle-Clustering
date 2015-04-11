def difference(x):
    return square_of_sum(x) - sum_of_squares(x)

def square_of_sum(x):
    return sum(xrange(1, x+1)) ** 2

def sum_of_squares(x):
    return sum(i  ** 2 for i in xrange(1, x+1))
