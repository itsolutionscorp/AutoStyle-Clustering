def difference(n):
	return square_of_sum(n) - sum_of_squares(n)

def square_of_sum(n):
	return pow(sum(x for x in range(1, n+1)), 2)
	
def sum_of_squares(n):
	return sum(x**2 for x in range(1, n+1))


if __name__ == '__main__':
	print sum_of_squares(5)
