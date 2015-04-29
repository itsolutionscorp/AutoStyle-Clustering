def square_of_sum(n):
	return sum(range(n+1)) ** 2
	
def sum_of_squares(n):
	total = 0
	for i in range(1, n+1):
		total += i**2
	return total 
	
def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
