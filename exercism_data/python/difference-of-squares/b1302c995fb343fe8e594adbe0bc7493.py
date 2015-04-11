def difference(number):
  return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(number):
  return sum(range(1, number + 1)) ** 2

def sum_of_squares(number):
  return reduce(lambda m, i: m + i ** 2, range(1, number + 1))
