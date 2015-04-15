def difference(seed):
	return square_of_sum(seed) - sum_of_squares(seed)

def square_of_sum(seed):
	return sum([i for i in range(1, seed + 1)]) ** 2

def sum_of_squares(seed):
	return sum([i ** 2 for i in range(1, seed + 1)])
