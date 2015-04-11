# difference_of_squares.py

def sum_of_squares(num):
	sumRet=0
	for i in range(1,num + 1):
		sumRet = sumRet + i ** 2
	return sumRet

def square_of_sum(num):
	return sum(range(1, num + 1)) ** 2
		
	
def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
