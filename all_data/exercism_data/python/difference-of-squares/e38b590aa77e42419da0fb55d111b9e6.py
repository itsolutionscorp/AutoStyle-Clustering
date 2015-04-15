def square_of_sum(num):
	i = 1
	sum_squared = 0
	while i <= num:
		sum_squared += i
		i += 1

	return sum_squared**2

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)

def sum_of_squares(num):
	i = 0
	square_sum = 0
	while i <= num:
		square_sum += i**2
		i += 1

	return square_sum
