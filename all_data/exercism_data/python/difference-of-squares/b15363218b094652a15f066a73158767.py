def square_of_sum(inp):
	sumOfInt = sum(range(0,inp+1))
	return sumOfInt*sumOfInt

def sum_of_squares(inp):
	sumOfSq = 0
	for i in range(inp+1):
		sumOfSq+=i*i
	return sumOfSq

def difference(inp):
	return square_of_sum(inp) - sum_of_squares(inp)
