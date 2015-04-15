def square_of_sum(n):
	return sum(range(1, n+1))**2

def sum_of_squares(n):
	return reduce(lambda s, i: s + i*i, range(1, n+1), 0)

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
