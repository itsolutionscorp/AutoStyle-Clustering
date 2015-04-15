#No checking for bounds is necessary since Python has built in large integers

def square_of_sum(num):
  return (sum(range(1,num+1)))**2  

def sum_of_squares(num):
  return sum([x*x for x in range(1,num+1)])

def difference(num):
  return square_of_sum(num) - sum_of_squares(num)

