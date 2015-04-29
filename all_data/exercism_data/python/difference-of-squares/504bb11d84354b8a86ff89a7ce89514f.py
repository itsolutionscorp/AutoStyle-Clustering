def sum_of_squares(number):
	#square all numbers and add to a counter
	count = 0
	for i in range(number+1):
		count += i*i
	return count

def square_of_sum(number):
	#using (n*n+1)/2 to sum, then squaring
	sum = number*(number+1)/2
	return sum*sum

def difference(number):
	#use our previous 2 functions
	return square_of_sum(number)-sum_of_squares(number)
