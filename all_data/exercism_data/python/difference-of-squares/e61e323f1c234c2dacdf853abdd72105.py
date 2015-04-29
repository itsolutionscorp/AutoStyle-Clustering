def square_of_sum(value):
  return sum(range(value+1)) ** 2

def sum_of_squares(value):
  return sum(x**2 for x in range(value+1))

def difference(value):
  return square_of_sum(value) - sum_of_squares(value)
