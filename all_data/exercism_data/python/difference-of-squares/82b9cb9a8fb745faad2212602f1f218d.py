def sum_of_squares(num):
	return sum([(i+1)**2 for i in xrange(num)])

def square_of_sum(num):
	return sum([(i+1) for i in xrange(num)])**2

def difference(num):
	return square_of_sum(num)-sum_of_squares(num)
