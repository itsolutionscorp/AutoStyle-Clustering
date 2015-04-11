def difference(x):
	return -(sum_of_squares(x) - square_of_sum(x))

def square_of_sum(x):
	sum = 0
	for i in range(1, x+1):
		sum += i
	return sum ** 2

def sum_of_squares(x): 
	sum = 0
	for i in range(1, x+1):
		sum += i**2
	return sum
