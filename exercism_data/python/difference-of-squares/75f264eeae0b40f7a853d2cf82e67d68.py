def difference(max_):
	return square_of_sum(max_) - sum_of_squares(max_)

def square_of_sum(max_):
	return sum(range(max_ + 1)) ** 2

def sum_of_squares(max_):
	return sum(x**2 for x in range(max_ + 1))
