def square_of_sum(N):
	return sum(range(N+1))**2

def sum_of_squares(N):
	sum = 0
	for i in range(N+1):
		sum += i**2
	return sum

def difference(N):
	return square_of_sum(N) - sum_of_squares(N)
