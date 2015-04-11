def square_of_sum(n):
	sum = 0
	for x in range(1, n+1):
		sum = sum + x
	return sum**2

def sum_of_squares(n):
	sum = 0
	for x in range(1, n+1):
		sum = sum + x**2
	return sum

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
