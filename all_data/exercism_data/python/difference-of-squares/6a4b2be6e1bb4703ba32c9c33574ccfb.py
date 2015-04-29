def difference(a):
	return square_of_sum(a) - sum_of_squares(a)

def square_of_sum(a):
	total = 0

	for x in xrange(1, a + 1):
		total += x

	return total ** 2

def sum_of_squares(a):
	total = 0

	for x in xrange(1, a + 1):
		total += x**2

	return total
