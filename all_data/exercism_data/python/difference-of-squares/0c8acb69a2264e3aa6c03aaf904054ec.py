def sum_of_squares(n):
	return sum([x ** 2 for x in range(n + 1)])

def square_of_sum(n):
	return sum([x for x in range(n + 1)]) ** 2
	
def difference(n):
	return abs(sum_of_squares(n) - square_of_sum(n))
