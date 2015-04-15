# -*- coding: utf-8 -*-
"""
Year related functions.
"""

def is_leap_year(year):

	if type(year) != int:
		return False

	if _evenly_divisible(year, 4):
		if _evenly_divisible(year, 100):
			if _evenly_divisible(year, 400):
				return True
			else:
				return False
		else:
			return True
	else:
		return False

def _evenly_divisible(number, d):
	return number % d == 0
