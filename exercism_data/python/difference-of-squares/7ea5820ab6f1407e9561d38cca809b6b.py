def sum_of_squares(num):
  numbers = range(1,num+1)
  sum = 0
  for i in numbers:
    sum += i**2

  return sum

def square_of_sum(num):
  numbers = range(1,num+1)
  sum = 0
  for i in numbers:
    sum += i
  return sum**2

def difference(num):
  return square_of_sum(num) - sum_of_squares(num)
