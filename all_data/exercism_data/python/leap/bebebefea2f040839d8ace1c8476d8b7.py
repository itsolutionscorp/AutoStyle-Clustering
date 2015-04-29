def is_leap_year(year):
	'''
	Function to test if input is a leap year.
	Returns True if leap year, else False.
	'''

	# leap year must be divisible by 4
	if year % 4 == 0:
		# but not 100, unless also divisible by 400
		if year % 100 == 0 and year % 400 != 0:
			return False
		else:
			return True

	return False
