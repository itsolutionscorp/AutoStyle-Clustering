def difference(num):
	return square_of_sum(num) - sum_of_squares(num)


def  square_of_sum(num):
	y = 0 
	for i in range(1,num +1):
		y += i
	return y**2


def sum_of_squares(num):
	y = 0
	for i in range(1,num+1):
		y += i**2
	return y
