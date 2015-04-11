
def square_of_sum(n):
	return sum([x+1 for x in xrange(n)])**2

def sum_of_squares(n):
	return sum([(x+1)**2 for x in xrange(n)])

def difference(n):
	return abs(square_of_sum(n) - sum_of_squares(n))
