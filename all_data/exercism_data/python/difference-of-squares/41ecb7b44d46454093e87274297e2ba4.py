def difference(x):
  return square_of_sum(x) - sum_of_squares(x)

def square_of_sum(x):
  summation = 0
  for i in range(0,x+1):
    summation += i
  return summation**2

def sum_of_squares(x):
  summation = 0
  for i in range(1,x+1):
    summation += i**2
  return summation
