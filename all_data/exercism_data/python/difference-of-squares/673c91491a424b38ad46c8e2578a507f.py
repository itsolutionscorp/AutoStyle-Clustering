def square_of_sum(num):
	counter=0
	for i in range(num):
		counter+=i+1
	return counter**2

def sum_of_squares(num):
	counter=0
	for i in range(num):
		counter+=((1+i)**2)
	return counter

def difference(num):
	counter=square_of_sum(num)-sum_of_squares(num)
	return counter
