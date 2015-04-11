def square_of_sum(n):
	summed = 0
	for i in xrange(1,n+1):
		summed += i
	return summed**2


def sum_of_squares(n):
	total = 0
	for i in xrange(1,n+1):
		total += i**2
	return total

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
