def sum_of_squares(num):
	sum= 0
	for i in range(1, num+1):
		sum+= i**2
	return sum	

def square_of_sum(num):
	return ((num+1)*num/2)**2

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)		
