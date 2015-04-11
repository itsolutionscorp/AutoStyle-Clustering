def square_of_sum(num):
	numbers = range(1, num + 1)
	return sum(numbers) ** 2

def sum_of_squares(num):
	numbers = range(1, num + 1)
	sum_squares = 0
	for number in numbers:
		sum_squares += number ** 2
	return sum_squares

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
