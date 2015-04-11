def difference(n):
	return abs(square_of_sum(n) - sum_of_squares(n))

def square_of_sum(n):
	return (n * (n+1) / 2) ** 2

def sum_of_squares(n):
	return (n / 6) * (n + 1) * (2*n + 1)
