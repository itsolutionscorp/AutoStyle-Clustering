def sum_of_squares(n):
	nums = range(1, n+1)
	squares = [x**2 for x in nums]
	return sum(squares)

def square_of_sum(n):
	nums = range(1, n+1)
	return sum(nums)**2

def difference(n):
	return square_of_sum(n) - sum_of_squares(n)
