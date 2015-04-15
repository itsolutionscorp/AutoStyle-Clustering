def difference(n):
	return square_of_sum(n)-sum_of_squares(n)
	
def square_of_sum(n):
	result = 0
	for i in range(n+1):
		result += i
	return result**2
	
def sum_of_squares(n):
	result = 0
	for i in range(n+1):
		result += i**2
	return result
