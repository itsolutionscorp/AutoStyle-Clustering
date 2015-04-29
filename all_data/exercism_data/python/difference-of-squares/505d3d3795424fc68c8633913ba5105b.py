def difference(n):
	return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
	total = 0
	for x in range(1, n+1):
		total += x
	return total**2


def sum_of_squares(n):
	total = 0
	for x in range(1, n+1):
		total += x**2
	return total
