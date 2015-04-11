def sum_of_squares(n):
  return n * (n + 1) * (n + 0.5) / 3

def square_of_sum(n):
  return 0.25 * n**2 * (n + 1)**2

def difference(n):
  return square_of_sum(n) - sum_of_squares(n)
