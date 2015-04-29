def square_of_sum(x):
	r = range(1, x+1) 
	return sum(r)**2

def sum_of_squares(x):
	r = range(1, x+1) 
	return sum(i**2 for i in r)

def difference(x):
	return square_of_sum(x) - sum_of_squares(x)
