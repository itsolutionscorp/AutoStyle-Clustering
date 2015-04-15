def difference(x):
	return abs(square_of_sum(x) - sum_of_squares(x))

def square_of_sum(x):
	return sum([i for i in range(x+1)])**2

def sum_of_squares(x):
	return sum([i**2 for i in range(x+1)])
