#!/usr/bin/env python

def square_of_sum(number):
	sum = 0
	while number > 0:
		sum += number
		number -= 1
	return sum ** 2

def sum_of_squares(number):
	sum_of_square = 0
	while number > 0:
		sum_of_square += number ** 2
		number -= 1
	return sum_of_square

def difference(number):
	square_of_a_sum = square_of_sum(number)
	sum_squared = sum_of_squares(number)
	return square_of_a_sum - sum_squared
