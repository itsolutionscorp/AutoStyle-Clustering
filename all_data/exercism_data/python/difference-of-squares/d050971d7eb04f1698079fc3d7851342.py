def square_of_sum(x):
	endsum = 0
	for p in xrange(x):
		endsum += p + 1
	return endsum**2

def sum_of_squares(x):
	endsum = 0
	for p in xrange(x):
		endsum += (p + 1)**2
	return endsum

def difference(x):
	return square_of_sum(x) - sum_of_squares(x)
