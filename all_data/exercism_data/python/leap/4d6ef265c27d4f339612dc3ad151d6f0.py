def is_leap_year(year):
	'''Check whether a given year is a leap year

	Arguments:
	year -- number representing year
	
	Returns: bool

	'''

	# check exceptions firts
	if year % 100 == 0 and year % 400 != 0:
		return False
	# check general rule
	return year % 4 == 0
