def sum_of_squares(number):
	num = 0
	for next_number in range(1, number + 1):
		num = num + next_number**2
	return num

def square_of_sum(number):
	num = 0
	for next_number in range(1, number + 1):
		num = num + next_number
	num = num ** 2
	return num

def difference(number):
	return square_of_sum(number) - sum_of_squares(number)
