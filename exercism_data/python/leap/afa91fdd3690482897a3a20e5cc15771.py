def is_leap_year(year=0):
	"""
	Checks if the given year is a leap year.
	"""
	result = False
	if year % 400 == 0:
		result = True
	elif year % 100 == 0:
		result = False
	elif year % 4 == 0:
		result = True
	else:
		result = False
	return result
