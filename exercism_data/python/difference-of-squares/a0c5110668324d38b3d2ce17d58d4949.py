def sum_of_squares(num):
	new = 0
	for i in range(1,num+1):
		new = new + i**2
	return new


def square_of_sum(num):
	new = 0
	for i in range(1,num+1):
		new = new + i
	square = new**2
	return square


def difference(num):
	return square_of_sum(num)-sum_of_squares(num)
