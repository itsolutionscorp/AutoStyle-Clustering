def square_of_sum(n):
	return sum(range(1,(1+n)))**2
	
def sum_of_squares(n):
	return sum([x**2 for x in range(1,(1+n))])
	
def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
