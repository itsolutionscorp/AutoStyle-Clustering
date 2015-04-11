# difference_of_squares

def difference(count):
	a = square_of_sum(count)
	b = sum_of_squares(count)
	return (a - b) if a > b else (b - a)

def square_of_sum(count):
	return sum( i for i in range(1, count + 1) ) ** 2

def sum_of_squares(count):
	return sum( i ** 2 for i in range(1, count + 1) )
