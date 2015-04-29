def difference(n):
  """ return diff """
  return abs(sum_of_squares(n) - square_of_sum(n))

def square_of_sum(n):
  """ square of sum """
  return sum(xrange(1,n+1))**2

def sum_of_squares(n):
  """ sum of squares """
  return sum(map(lambda x: x**2, xrange(1,n+1)))
