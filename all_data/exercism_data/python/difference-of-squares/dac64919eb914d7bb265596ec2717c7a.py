def square_of_sum(N):
	""" This function takes a natural number N and squares the sum of the first N numbers """
	return sum(range(N + 1))**2

def sum_of_squares(N):
	""" This function takes a natural number N and returns the sum of the squares of the first N numbers"""
	return sum([i**2 for i in range(N + 1)])

def difference(N):
	""" This fucntion takes a natural number N and returns the difference of squares for the first N."""
	return square_of_sum(N) - sum_of_squares(N)
