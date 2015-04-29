def difference(n):
  return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
  return ((n**2+n)/2)**2

def sum_of_squares(n):
  return (2*n**3 + 3*n**2 + n)/6
