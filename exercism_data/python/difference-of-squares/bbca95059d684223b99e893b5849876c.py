def difference(max):
	return square_of_sum(max) - sum_of_squares(max)

def square_of_sum(max):
	sum = 0
	max = max + 1
	for i in range(1, max):
		sum+=i
	return sum**2

def sum_of_squares(max):
	sum = 0
	max = max + 1
	for i in range(1, max):
		sum+=i**2
	return sum
