def sum_of_squares(x):
	toReturn = 0
	for i in range(1, x+1):
		toReturn += i**2
	return toReturn

def square_of_sum(x):
	return sum(range(1, x+1))**2

def difference(x):
	return square_of_sum(x)-sum_of_squares(x)
