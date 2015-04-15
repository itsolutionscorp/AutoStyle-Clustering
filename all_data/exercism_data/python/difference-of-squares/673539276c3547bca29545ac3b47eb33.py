# exercism-python: difference of squares

def square_of_sum(n):
	'''Square the summation from 1 to n, inclusive.'''
	return sum(i for i in range(1,n+1))**2

def sum_of_squares(n):
	'''Summation from 1**2 to n**2, inclusive.'''
	return sum(i**2 for i in range(1,n+1))

def difference(n):
	'''Difference between square of sum and sum of squares.'''
	return square_of_sum(n) - sum_of_squares(n)
