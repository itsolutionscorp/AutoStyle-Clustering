# the sum of the squares of the first n positive integers
# note: the sum of the squares of the first n positive integers is n*(n+1)*2*n+1)/6
# citation: http://en.wikipedia.org/wiki/Faulhaber%27s_formula
def sum_of_squares(n):
	return int(n*(n+1)*(2*n+1)/6)

# the square of the sum of the first n positive integers
# note: the sum of the first n positive integers is n*(n+1)/2
# citation: http://en.wikipedia.org/wiki/Faulhaber%27s_formula
def square_of_sum(n):
	return int(n*(n+1)/2)**2

# calculates the square of the sum of the first n positive integers minus the sum of the squares of the first n positive integers
def difference(n):
	return square_of_sum(n)-sum_of_squares(n)
