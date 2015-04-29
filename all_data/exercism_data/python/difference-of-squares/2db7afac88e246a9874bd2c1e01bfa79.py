def sum_of_squares(n):
	#using series
	
	return (n*(n+1)*(2*n + 1))/6
	

def square_of_sum(n): 
	#also using series

	return (n*(n+1)/2)**2
	
def difference(n): 
	#difference between sum_of_squares and square_of_sum for first n natural #s
	#square of sum will always be larger, so no need to do absolute value
	return square_of_sum(n) - sum_of_squares(n)
