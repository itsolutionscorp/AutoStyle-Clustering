def square_of_sum(num):
	x = 0
	for y in range(1,num+1):
		x += y
	return x**2


def sum_of_squares(num):
	x = 0
	for y in range(1,num+1):
		# print x, y 
		x += y**2
	return x

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)


if __name__ == '__main__':
	print sum_of_squares(10)
	print square_of_sum(10)
	print difference(10)
