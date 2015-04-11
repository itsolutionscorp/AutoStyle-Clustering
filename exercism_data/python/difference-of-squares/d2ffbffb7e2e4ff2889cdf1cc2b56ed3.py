#calculates the square of the sums of the first N nat numbers
def square_of_sum(n):
	s = 0
	for i in range(1,n+1): s += i
	return s**2

#calculates the sums the squares of the first N nat numbers
def sum_of_squares(n):
	s = 0
	for i in range(1,n+1): s += i**2
	return s

#calculates the difference between square_of_sum and sum_of_squares
def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
