def difference(n):
  return abs(square_of_sum(n) - sum_of_squares(n))

def square_of_sum(n):
  return sum(range(n+1))**2

def sum_of_squares(n):
  return sum(n**2 for n in range(n+1))
