###################################
# Function calculates the square of the sum of  the
#		first n numbers
# inputs: number (indicates the first n numbers to sum)
# returns: 	number
def square_of_sum(n):
	answer = sum(range(n+1))
	answer = answer**2
	return answer
	
	
###################################
# Function calculates the sum of the squares of  the
#		first n numbers
# inputs: number (indicates the first n numbers to sum)
# returns: 	number
def sum_of_squares(n):
	answer = n*(n+1)*(2*n+1)/6
	return answer

###################################
# Function the difference between the sum of the 
#		squares and the square of the sums of the 
#		first N natural numbers.
# inputs: number (indicates the first n numbers to sum)
# returns: 	number
def difference(n):
	answer = sum_of_squares(n) - square_of_sum(n)
	return abs(answer)
