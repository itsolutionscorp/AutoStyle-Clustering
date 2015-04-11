#This is a program that will take the sum of the squares of all the
#natural numbers, and subtract it from the square of the sum of the 
#first ten natural numbers.

def square_of_sum(num):
	return ((num ** 2 + num) / 2) ** 2

def sum_of_squares(num):
	return sum([i ** 2 for i in range(num + 1)])

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
