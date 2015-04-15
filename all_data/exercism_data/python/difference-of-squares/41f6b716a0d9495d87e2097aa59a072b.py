from numpy import power

def square_of_sum(n):
	""" Calculates the square of sum of first N natural numbers
	
	Keyword arguments:
	n -- natural number
	"""
	return ((n+1)*n/2)**2

def sum_of_squares(n):
	""" Calculates the sum of squares of first N natural numbers
	
	Keyword arguments:
	n -- natural number
	"""
	
	return n*(n+1)*(2*n+1)/6
	#return (power(range(1,n+1),2).sum())

def difference(n):
	""" Calculates the difference between square of sum and sum of squares of first N natural numbers
	
	Keyword arguments:
	n -- natural number
	"""
	if type(n) is not int or n < 0:
		raise ValueError('arg must be a natural number')
	return square_of_sum(n) - sum_of_squares(n)


# Standalone debugging
if __name__ == '__main__':
	print(difference(10))
