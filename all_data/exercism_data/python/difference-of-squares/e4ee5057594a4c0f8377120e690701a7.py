def square_of_sum(num):
	sos = 0
	for i in range(num,0,-1):
		sos += i
	return sos**2
def sum_of_squares(num):
	sos = 0
	for i in range(num,0,-1):
		sos += i**2
	return sos
def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
