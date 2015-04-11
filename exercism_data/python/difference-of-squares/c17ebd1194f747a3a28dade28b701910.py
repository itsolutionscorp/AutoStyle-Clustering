def difference(num):
	return square_of_sum(num) - sum_of_squares(num)



def square_of_sum(num):
	total = 0
	for i in range(1, num+1):
		total += i

	total = total**2
	return total



def sum_of_squares(num):
	total = 0
	for i in range(1, num+1):
		total += i**2

	return total
