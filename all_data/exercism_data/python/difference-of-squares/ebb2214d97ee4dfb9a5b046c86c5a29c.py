"""
returns the difference between the square of sums
and the sum of squares of first n natural numbers
"""
def difference(n):
    return square_of_sum(n) - sum_of_squares(n)

"""
returns the square of the sum of the first
n natural numbers
"""
def square_of_sum(n):
    return sum(xrange(1, n+1))**2


"""
returns the sum of the squares of the
first n natural numbers
"""
def sum_of_squares(n):
    return sum(i**2 for i in xrange(1, n+1))
