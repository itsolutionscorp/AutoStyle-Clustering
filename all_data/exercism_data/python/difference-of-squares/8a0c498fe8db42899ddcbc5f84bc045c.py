def difference(number):
  return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(number):
  result = sum(range(1, number + 1))
  return result ** 2

def sum_of_squares(number):
  result = sum(map(lambda i: i ** 2, range(1, number + 1)))
  return result
