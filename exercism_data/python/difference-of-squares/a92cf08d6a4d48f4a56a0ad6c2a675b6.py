def difference(number):
	# Compute and return the difference
	# between the square of the sum and
	# the sum of the squares of the given
	# number.
	return square_of_sum(number) - sum_of_squares(number)

def square_of_sum(count):
	# Compute the square of the sum of 
	# the given count of natural numbers.
	
	sum = 0
	
	# Loop _count_ times, computing the sum.
	for number in range (count):
		sum += (number+1)
	
	# Return the square of the sum.
	return sum**2

def sum_of_squares(count):
	# Compute the sum of the squares
	# of the given count of natural numbers.
	
	sum = 0
	
	# Loop _count_ times, computing the sum of the squares.
	for number in range (count):
		sum += (number+1)**2
	
	# Return the sum.
	return sum
