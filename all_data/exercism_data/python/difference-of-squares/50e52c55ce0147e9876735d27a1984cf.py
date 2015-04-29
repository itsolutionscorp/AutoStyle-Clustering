def difference(n):
	return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
	return sum(d for d in range(1, n+1))**2

def sum_of_squares(n):
	return sum(d**2 for d in range(1, n+1))
