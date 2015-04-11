"""
Tests if a year is a leap year.
Written by Bede Kelly for Exercism.
"""

__author__ = "Bede Kelly"

def is_leap_year(year):
	"""Returns a boolean indicating the leapyearishness of the given year."""
	retval = False
	if year % 4 == 0:
		retval = True
		if year % 100 == 0:
			retval = False
			if year % 400 == 0:
				retval = True
	return retval

	# Order of operations and 0 being falsy, woo!
	# return (not year%4) and (not year%400 or year%100)
