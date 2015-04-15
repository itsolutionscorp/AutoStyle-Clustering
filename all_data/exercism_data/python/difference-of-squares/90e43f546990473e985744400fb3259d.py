def difference(n):
	return abs(square_of_sum(n) - sum_of_squares(n))

def square_of_sum(n):
	return (n * (n+1) / 2) ** 2

def sum_of_squares(n):
	total = 0
	for i in xrange(n):
		total += (i+1) ** 2
	return total
