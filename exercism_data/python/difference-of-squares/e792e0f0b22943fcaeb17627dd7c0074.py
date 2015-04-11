'''difference_of_squares.py
	created 29 Sept 2014
	by @jestuber'''


def square_of_sum(num):
	return sum(range(1,num+1))**2

def sum_of_squares(num):
	return sum(n**2 for n in range(1,num+1))

def difference(num):
	return square_of_sum(num) - sum_of_squares(num)
