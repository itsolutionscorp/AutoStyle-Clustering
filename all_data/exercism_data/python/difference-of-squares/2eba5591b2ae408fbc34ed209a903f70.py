def sum_of_squares(n):
	
	my_sum = 0
	for i in range(1,n+1):
		my_sum += i*i
	
	return my_sum

def square_of_sum(n):
	
	my_sum = 0
	for i in range(1,n+1):
		my_sum += i
	my_sum *= my_sum
	
	return my_sum

def difference(n):
	return square_of_sum(n) - sum_of_squares(n) 
