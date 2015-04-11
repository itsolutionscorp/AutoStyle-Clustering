def square_of_sum(N):
	return sum(range(1,N+1))**2

def sum_of_squares(N):
	return sum(x**2 for x in range(1,N+1))

def difference(N):
	return abs(sum_of_squares(N) - square_of_sum(N))
