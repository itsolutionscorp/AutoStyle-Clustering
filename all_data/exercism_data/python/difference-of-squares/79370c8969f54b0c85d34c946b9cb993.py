from math import pow

def square_of_sum(length):
	base = sum(range(1, length+1))
	return int(base**2)

def sum_of_squares(length):
	squares = [int(number**2) for number in range(1, length+1)]
	return sum(squares)

def difference(length):
	return square_of_sum(length) - sum_of_squares(length)
