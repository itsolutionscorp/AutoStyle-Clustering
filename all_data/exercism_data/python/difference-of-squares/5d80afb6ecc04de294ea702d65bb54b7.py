def difference(i):
    return square_of_sum(i) - sum_of_squares(i)

def square_of_sum(i):
    return sum(xrange(1, i + 1))**2

def sum_of_squares(i):
    return sum(i**2 for i in xrange(1, i + 1))
