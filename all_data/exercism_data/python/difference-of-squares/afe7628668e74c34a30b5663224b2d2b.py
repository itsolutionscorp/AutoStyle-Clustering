def difference(num):
	return abs(square_of_sum(num) - sum_of_squares(num))

def square_of_sum(num):
	return sum(range(1, num + 1)) ** 2

def sum_of_squares(num):
	summed = 0
	for x in range(1, num + 1):
		summed += x ** 2

	return summed
