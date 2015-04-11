def difference(number):
  return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(number):
  sum = 0
  for i in range(1, number + 1):
    sum += i
  return sum ** 2

def sum_of_squares(number):
  result = 0
  for i in range(1, number + 1):
    result += i ** 2
  return result
