def difference(count):
	return square_of_sum(count) - sum_of_squares(count)

def sum_of_squares(count):
	count = range(count + 1)
	return sum([x**2 for x in count])

def square_of_sum(count):
	count = range(count + 1)
	return sum(count)**2
