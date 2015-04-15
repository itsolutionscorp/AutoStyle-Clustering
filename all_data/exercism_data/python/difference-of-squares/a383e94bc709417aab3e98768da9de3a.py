# Difference of squares

# square the summation from 1 to n
def square_of_sum(n):
	summation = 0
	for i in range(1,n+1):
		summation += i
	return summation ** 2

# summation from 1**2 to n**2
def sum_of_squares(n):
	summation = 0
	for i in range(1,n+1):
		summation += i**2
	return summation

# difference between square of sum and sum of squares
def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
