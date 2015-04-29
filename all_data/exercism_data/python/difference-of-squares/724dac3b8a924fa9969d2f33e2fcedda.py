def square_of_sum(to):
	return int(to * (to + 1) / 2) ** 2

def sum_of_squares(to):
	return int((to * (to + 1) * (2 * to + 1)) / 6)

def difference(to):
	return square_of_sum(to) - sum_of_squares(to)
