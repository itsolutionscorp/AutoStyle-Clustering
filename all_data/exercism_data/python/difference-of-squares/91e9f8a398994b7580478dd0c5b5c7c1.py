def difference(n):
  return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
  sum = 0
  for i in xrange(1, n+1):
    sum = sum + i
  return sum**2

def sum_of_squares(n):
  sum = 0
  for i in xrange(1, n+1):
    sum = sum + i**2
  return sum
