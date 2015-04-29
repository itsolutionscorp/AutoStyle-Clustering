def difference(maxnum):
	return square_of_sum(maxnum) - sum_of_squares(maxnum)

def square_of_sum(maxnum):
	total = 0
	for num in range(1,maxnum + 1):
		total += num
	return total**2

def sum_of_squares(maxnum):
	total = 0
	for num in range(1,maxnum + 1):
		total += num**2
	return total
