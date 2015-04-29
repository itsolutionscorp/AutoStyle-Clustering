def sum_of_squares(n):
  return sum([x*x for x in xrange(n+1)])

def square_of_sum(n):
  x = sum(xrange(n+1))
  return x*x

def difference(n):
  return square_of_sum(n) - sum_of_squares(n)
