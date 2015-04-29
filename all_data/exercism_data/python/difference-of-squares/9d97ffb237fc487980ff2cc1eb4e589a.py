def square_of_sum(N):
	""" This function takes a natural number N and squares the sum of the first N numbers """
	sum = 0
	while N > 0:
		sum += N
		N -= 1
	return sum**2

def sum_of_squares(N):
	""" This function takes a natural number N and returns the sum of the squares of the first N numbers"""
	sum = 0
	while N > 0:
		sum += N**2
		N -= 1
	return sum

def difference(N):
	""" This fucntion takes a natural number N and returns the difference of squares for the first N."""
	return square_of_sum(N) - sum_of_squares(N)
