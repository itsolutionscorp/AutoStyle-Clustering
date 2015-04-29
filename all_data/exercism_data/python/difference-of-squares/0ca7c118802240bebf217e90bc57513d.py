def difference(input):
  return square_of_sum(input) - sum_of_squares(input)
def square_of_sum(input):
  return ((1+input) * (input /2.0)) **2
def sum_of_squares(input):
  return sum( iter ** 2 for iter in range (input+1))  
