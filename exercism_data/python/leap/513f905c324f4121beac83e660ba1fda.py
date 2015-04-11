def is_leap_year(year):
	"""
	Returns a boolean value whether the given year is a leap year.
	
	Arguments:
	year - integer
	"""
	
	if year % 4 is not 0:
		return False
		
	if year % 400 is 0:
		return True
		
	return year % 100 is not 0
