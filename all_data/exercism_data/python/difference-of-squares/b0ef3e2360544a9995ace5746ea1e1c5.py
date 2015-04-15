def sum_of_squares(N):
	return sum(n*n for n in range(1,N+1))

def square_of_sum(N):
	return sum(range(1,N+1))**2

def difference(N):
	return abs(sum_of_squares(N) - square_of_sum(N))
