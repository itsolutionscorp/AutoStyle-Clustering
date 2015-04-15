def difference(x):
	return square_of_sum(x) - sum_of_squares(x)

def square_of_sum(x):
	return sum(range(0,x+1))**2

def sum_of_squares(x):
	return sum({a**2 for a in range(0,x+1)})
