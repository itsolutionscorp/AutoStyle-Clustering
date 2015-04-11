def square_of_sum(n):
	s = sum(xrange(1, n+1))
	return s * s

def sum_of_squares(n):
	return sum(n * n for n in xrange(1, n+1))

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
