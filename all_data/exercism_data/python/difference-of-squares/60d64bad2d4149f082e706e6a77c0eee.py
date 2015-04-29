def difference(num):
  return square_of_sum(num) - sum_of_squares(num)

def square_of_sum(num):
  return (num*(1+num)/2) * (num*(1+num)/2)

def sum_of_squares(num):
  return num*(num+1)*(2*num+1)/6
