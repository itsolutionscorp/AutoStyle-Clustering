def difference(d):
	"""
	Input:	Integer d.
	Output:	Integer square_of_sum(d) - sum_of_squares(d).
	"""
	return square_of_sum(d) - sum_of_squares(d)

def square_of_sum(b):
	"""
	Input:	Integer b.
	Output:	Integer which summates 1 to b squared.
	"""
	result = 0
	for i in range(1, b + 1):
		result += i
	return result**2

def sum_of_squares(c):
	"""
	Input:	Integer c.
	Output: Integer which summates 1 squared to c squared.
	"""
	result = 0
	for i in range(1, c + 1):
		result += i**2
	return result
