
def square_of_sum(x):
	total = 0
	for i in range(1, x+1):
		total += i
	total = total ** 2
	return total
	
def sum_of_squares(x):
	total = 0
	for i in range(1, x+1):
		total += i ** 2
	return total
	
def difference(x):
	return  square_of_sum(x) - sum_of_squares(x)
