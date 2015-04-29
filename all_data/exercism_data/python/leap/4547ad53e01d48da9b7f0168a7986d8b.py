#!/usr/bin/env python3

def is_leap_year(year):
	"""
	Report if a given year is a leap year.
	The tricky thing here is that a leap year occurs:

	on every year that is evenly divisible by 4
	  except every year that is evenly divisible by 100
	    unless the year is also evenly divisible by 400
	"""
	try:
		return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)
	except TypeError:
		print("Error: '{}' is not a valid year. Please enter an integer.".format(year))
