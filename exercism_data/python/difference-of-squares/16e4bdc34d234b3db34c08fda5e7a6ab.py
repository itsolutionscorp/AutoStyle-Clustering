def square_of_sum(n):
    return sum(xrange(n+1))**2

def sum_of_squares(n):
    return sum(map(lambda x: x**2, xrange(n+1)))

def difference(n):
    return square_of_sum(n)-sum_of_squares(n)
