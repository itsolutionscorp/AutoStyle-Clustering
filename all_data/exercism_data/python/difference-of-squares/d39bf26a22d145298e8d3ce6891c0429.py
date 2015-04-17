def square_of_sum(num):
	sum = 0
	for i in range(num+1):
		sum = sum + i
	return sum**2

def sum_of_squares(num):
	sum = 0
	for i in range(num+1):
		sum = sum + i**2
	return sum

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)