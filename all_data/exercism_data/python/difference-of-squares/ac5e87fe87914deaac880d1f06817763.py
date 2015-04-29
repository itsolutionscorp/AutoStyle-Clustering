from math import pow

def square_of_sum(length):
	base = reduce(lambda x,y: x+y, xrange(1, length+1))
	return int(pow(base, 2))

def sum_of_squares(length):
	squares = [int(pow(number, 2)) for number in xrange(1, length+1)]
	return reduce(lambda x,y: x+y, squares)

def difference(length):
	return square_of_sum(length) - sum_of_squares(length)
