def square_of_sum(num):
  total = 0
  for i in range(1,num+1):
    total += i
  return total**2

def sum_of_squares(num):
  total = 0
  for i in range(1,num+1):
    total += i**2
  return total

def difference(num):
  total = sum_of_squares(num) - square_of_sum(num)
  return abs(total)
