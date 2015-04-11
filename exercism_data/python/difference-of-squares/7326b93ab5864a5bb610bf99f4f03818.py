def sum_of_squares(n):
	#sum of squares of first n natural numbers (1...n) inclusive
	# square each number in range, then add them all together
	#requires loop, sum var to keep track of sum
	
	sum = 0
	
	for num in range(1, n+1): 
		sum = sum + num**2

	return sum
	

def square_of_sum(n): 
	#add up all numbers first, then take the sum to the power of 2
	sum = 0
	
	for num in range(1, n+1): 
		sum = sum + num

	return sum**2
	
def difference(n): 
	#difference between sum_of_squares and square_of_sum for first n natural #s
	#square of sum will always be larger, so no need to do absolute value
	return square_of_sum(n) - sum_of_squares(n)
