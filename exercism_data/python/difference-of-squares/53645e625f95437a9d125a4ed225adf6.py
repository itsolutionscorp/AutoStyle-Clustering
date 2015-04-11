def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
def square_of_sum(num):
	x = 1
	sq_sum = 0
	while x <= num:
		sq_sum = sq_sum + x
		x = x + 1
	return sq_sum**2
def sum_of_squares(num):
	x = 1
	sq_sum = 0
	while x <= num:
		sq_sum = sq_sum + x**2
		x = x + 1
	return sq_sum
