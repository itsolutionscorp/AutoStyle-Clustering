def square_of_sum(a):
	return sum([x for x in range(1, a + 1)]) ** 2

def sum_of_squares(a):
	return sum([x**2 for x in range(1, a + 1)])

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
