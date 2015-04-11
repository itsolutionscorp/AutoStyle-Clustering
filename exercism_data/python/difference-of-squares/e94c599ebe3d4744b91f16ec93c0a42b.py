def difference(end):
	return square_of_sum(end) - sum_of_squares(end)

def square_of_sum(end):
	return sum(range(1, end+1))**2

def sum_of_squares(end):
	return sum(i**2 for i in range(1, end+1))
