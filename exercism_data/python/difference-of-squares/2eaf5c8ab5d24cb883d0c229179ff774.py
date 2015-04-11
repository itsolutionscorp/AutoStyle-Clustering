def square_of_sum(n):
	s = 0
	for i in range(1,n+1):
		s += i
	return s**2

def sum_of_squares(n):
	s = 0
	for i in range(1,n+1):
		s += i**2
	return s
	
def difference(n):
	return square_of_sum(n)-sum_of_squares(n)
