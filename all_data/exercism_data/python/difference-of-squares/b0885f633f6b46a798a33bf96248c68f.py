def square_of_sum(number):
	answer = 0
	for x in range(1, number+1):
		answer+=x	
	return answer**2
def sum_of_squares(number):
	answer = 0
	for x in range(1, number+1):
		answer+=x**2
	return answer
def difference(number):
	return square_of_sum(number) - sum_of_squares(number)
