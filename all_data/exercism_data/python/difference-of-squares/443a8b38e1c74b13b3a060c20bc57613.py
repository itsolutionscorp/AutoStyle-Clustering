def square_of_sum(number):
	total_holder = 0
	for x in range(1,number+1):
		total_holder += x
	total_holder = total_holder ** 2
	return total_holder
def sum_of_squares(number):
	total_holder = 0
	for x in range(1,number+1):
		total_holder += x**2
	total_holder = total_holder
	return total_holder
def difference(number):
	return abs(sum_of_squares(number) - square_of_sum(number))
