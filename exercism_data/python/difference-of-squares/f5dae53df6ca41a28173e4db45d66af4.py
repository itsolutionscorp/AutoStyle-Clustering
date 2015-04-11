def square_of_sum(inp):
	return sum(range(0,inp+1))**2

def sum_of_squares(inp):
	return sum([x**2 for x in range(1,inp+1)])

def difference(inp):
	return square_of_sum(inp) - sum_of_squares(inp)
