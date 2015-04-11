def sum_of_squares(limit):
	total = 0
	for num in range(1, limit+1):
		total += (num**2)
	return total
	

def square_of_sum(limit):
	total = 0
	for num in range(1, limit+1):
		total += num
	return total**2

def difference(limit):
	return abs(square_of_sum(limit) - sum_of_squares(limit))
		
