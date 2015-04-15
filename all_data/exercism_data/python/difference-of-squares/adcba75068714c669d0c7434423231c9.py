def euler_sum(num):      return num*(num+1)/2

def square_of_sum(num):  return euler_sum(num) ** 2
def sum_of_squares(num): return euler_sum(num) * (2*num+1)/3

def difference(num):     return square_of_sum(num) - sum_of_squares(num)
