def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

def sum_of_squares(n):
    return sum(n**2 for n in xrange(1, n+1))

def square_of_sum(n):
    return sum(n+1 for n in xrange(0, n))**2
