def square_of_sum(a):
	x = 0

	for number in range(1, a+1):
		x += number

	return x ** 2

def sum_of_squares(b):
	x = 0

	for number in range(1, b+1):
		x += number ** 2

	return x

def difference(c):
	if square_of_sum(c) > sum_of_squares(c):
		return square_of_sum(c) - sum_of_squares(c)

	if sum_of_squares(c) > square_of_sum(c):
		return sum_of_squares(c) - square_of_sum(c)
