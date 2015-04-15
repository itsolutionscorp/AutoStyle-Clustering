#!/usr/bin/python

def calc(n):
	global sum_of_squares, square_of_sum, difference;
	sum_of_squares = 0
	sum_of_digits = 0
	for i in xrange(1,n+1):
		sum_of_squares += i * i
		sum_of_digits += i
	square_of_sum = sum_of_digits ** 2
	difference = square_of_sum - sum_of_squares
	return
	
def sum_of_squares(n):
	calc(n)
	return sum_of_squares
	
def square_of_sum(n):
	calc(n)
	return square_of_sum
	
def difference(n):
	calc(n)
	return difference
	
