
def square_of_sum(number):
  square_f = 0

  for i in range(0, number + 1):
    square_f += i

  return square_f**2

def sum_of_squares(number):
  sum_f = 0

  for i in range(0, number + 1):
    sum_f += i**2

  return sum_f

def difference(number):
  return square_of_sum(number) - sum_of_squares(number)


