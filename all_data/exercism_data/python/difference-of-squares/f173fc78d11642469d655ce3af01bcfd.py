def difference(integer):
	return square_of_sum(integer) - sum_of_squares(integer)

def square_of_sum(integer):
	return ((integer*(integer+1))**2)/4

def sum_of_squares(integer):
	return (integer*(integer+1)*(2*integer+1))/6
