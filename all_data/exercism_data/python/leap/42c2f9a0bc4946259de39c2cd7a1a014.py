def is_leap_year(year):
	"""
	Input: Integer year.
	Output: True if the year is a leap year, False if not.
	"""
	return (year % 4 == 0) and (year % 400 == 0 or year % 100 != 0)
