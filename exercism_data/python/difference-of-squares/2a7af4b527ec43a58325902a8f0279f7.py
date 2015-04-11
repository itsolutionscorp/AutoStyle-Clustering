def difference(limit):
	diff = square_of_sum(limit) - sum_of_squares(limit)
	return diff

def square_of_sum(limit):
	total = 0
	for x in range(limit + 1):
		total += x
	return total**2

def sum_of_squares(limit):
	total = 0
	for x in range(limit + 1):
		total += x**2
	return total
