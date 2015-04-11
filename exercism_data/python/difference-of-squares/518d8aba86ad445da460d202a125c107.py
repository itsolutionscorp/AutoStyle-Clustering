def square(number):
	return number * number

def square_of_sum(number):
	return square(sum(range(1, number + 1)))
	
def sum_of_squares(number):
	return sum(map(square, range(1, number + 1)))
	
def difference(number):
	return square_of_sum(number) - sum_of_squares(number)
