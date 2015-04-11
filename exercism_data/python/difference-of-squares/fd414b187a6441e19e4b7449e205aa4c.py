def difference(i):
    return abs(sum_of_squares(i) - square_of_sum(i))

def square_of_sum(i):
    return sum(xrange(1, i + 1))**2

def sum_of_squares(i):
    return sum(i**2 for i in xrange(1, i + 1))

