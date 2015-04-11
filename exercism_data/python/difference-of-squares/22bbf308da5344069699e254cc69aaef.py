from math import fabs

def difference(n):
	return int(fabs(square_of_sum(n) - sum_of_squares(n)))

def square_of_sum(n):
	return sum(xrange(n + 1)) ** 2

def sum_of_squares(n):
	return sum([i**2 for i in xrange(n+1)])
