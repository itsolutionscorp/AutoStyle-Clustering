def sum_of_squares(end):
	sum = 0
	for x in range(1,end+1):
		sum += x**2

	return sum

def square_of_sum(end):
	sum = 0
	for x in range(1,end+1):
		sum += x

	return sum**2

def difference(end):
	return abs(square_of_sum(end) - sum_of_squares(end))
