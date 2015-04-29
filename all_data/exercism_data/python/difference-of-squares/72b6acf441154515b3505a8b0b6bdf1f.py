def difference(integer):
	return square_of_sum(integer) - sum_of_squares(integer)

def square_of_sum(integer):
	return sum(get_integers(integer))**2

def sum_of_squares(integer):
	return sum(i*i for i in get_integers(integer))

def get_integers(integer):
	return range(1, integer+1)
