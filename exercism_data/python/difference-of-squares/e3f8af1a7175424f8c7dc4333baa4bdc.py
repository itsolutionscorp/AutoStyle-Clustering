def square_of_sum(number):
  sum = 0
  for i in range(0, number):
    sum += (i + 1)
  return sum ** 2

def sum_of_squares(number):
  sum = 0
  for i in range(0, number):
    sum += (i + 1) ** 2
  return sum

def difference(number):
  return square_of_sum(number) - sum_of_squares(number)
