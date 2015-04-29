def difference(i):
	return square_of_sum(i) - sum_of_squares(i)

def square_of_sum(i):
	return sum([x for x in range(0, i+1)])**2

def sum_of_squares(i):
	return sum([x**2 for x in range(0, i+1)])
