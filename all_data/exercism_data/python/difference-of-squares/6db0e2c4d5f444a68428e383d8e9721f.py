def difference(n):
	return square_of_sum(n) - sum_of_squares(n)


def sum_of_squares(n):
	return sum((i+1)**2 for i in xrange(n))
	
def square_of_sum(n):
	# (1 + 2 + ... + 10)**2 = 55**2 = 3025
	return sum(xrange(1, n+1))**2
