def square_of_sum(n):
	return (n * (n + 1) / 2) ** 2

def sum_of_squares(n):
	return reduce(lambda x, y: x + y, (n * n for n in xrange(1, n+1)))

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
