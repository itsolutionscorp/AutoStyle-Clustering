def square_of_sum(n):
	sum = (n*(n+1))/2

	return sum**2

def sum_of_squares(n):
	sum = 0

	while n > 0:
		sum = sum + n**2
		n = n - 1

	return sum


def difference(n):

	return square_of_sum(n) - sum_of_squares(n)
