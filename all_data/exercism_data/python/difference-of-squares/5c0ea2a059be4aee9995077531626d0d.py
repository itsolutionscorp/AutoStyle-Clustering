def difference(n):
	return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
	return sum(range(1, n+1))**2

def sum_of_squares(n):
	if n == 0:
		return 0
	else:
		return n**2 + sum_of_squares(n-1)
