def difference(number):
	return square_of_sum(number) - sum_of_squares(number)
	
def square_of_sum(number):
	sum = 0
	for num in range(0, number + 1):
		sum = sum + num
		
	return sum**2
	
def sum_of_squares(number):
	sum = 0
	for num in range(0, number + 1):
		sum = sum + num**2
		
	return sum
