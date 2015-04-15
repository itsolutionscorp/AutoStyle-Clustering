def difference(num):
	return square_of_sum(num)-sum_of_squares(num)
def sum_of_squares(num):
	summ=0
	for i in range(num+1):
		summ=summ+i*i
	return summ
def square_of_sum(num):
	summ=0
	for i in range(num+1):
		summ=summ+i
	return summ*summ
