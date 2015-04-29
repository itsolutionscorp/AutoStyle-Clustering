def square_of_sum(n):

	sum = n * (n + 1) / 2
	return sum * sum

def sum_of_squares(n):

	sum = 0
	for i in xrange(1, n+1):
		sum += i * i
	return sum

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
