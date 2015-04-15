"""
Difference between the sum of squares and the square of the sums.
Written by Bede Kelly for Exercism.
"""

__author__ = "Bede Kelly"

def square_of_sum(num):
	"""Returns the square of the sum of the integers up to the given number."""
	return sum(range(num+1)) ** 2

def sum_of_squares(num):
	"""Returns the sum of the squares of the integers up to the given number."""
	return sum(x**2 for x in range(num+1))

def difference(num):
	"""Returns the difference between the sum of the squares and the square of
	the sums."""
	return square_of_sum(num) - sum_of_squares(num)
