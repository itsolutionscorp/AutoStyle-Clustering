def difference(number): 
  square_of_the_sum = square_of_sum(number)
  sum_of_the_squares = sum_of_squares(number)
  return square_of_the_sum - sum_of_the_squares

def square_of_sum(number):
  numbers = range(1,number+1)
  numbers_sum = sum(numbers) 
  return numbers_sum**2 

def sum_of_squares(number):
  numbers = squares(number)
  return sum(numbers)

def squares(number):
  numbers = range(1,number+1)
  return [n**2 for n in numbers]
