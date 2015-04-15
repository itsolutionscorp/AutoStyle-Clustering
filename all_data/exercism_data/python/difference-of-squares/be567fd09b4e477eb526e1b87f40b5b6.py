# Difference of Squares

def sum(n):
	if (n == 1):
		return 1
	else:
		return n + sum(n-1)

def square_of_sum(n):
	return sum(n)**2

def sum_of_squares(n):
	if (n == 1):
		return 1
	else:
		return n**2 + sum_of_squares(n-1)

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
