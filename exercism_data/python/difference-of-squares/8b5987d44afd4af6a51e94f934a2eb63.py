def square_of_sum(num):
	# Sum everything from 1 through entered num and then raise it to the 2nd power
	return sum(range(1,num+1))**2

def sum_of_squares(num):
	# Create an array of squares of the 1 - num and then add them together
	return sum(list(map(lambda x: x**2,range(1,num+1))))

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
