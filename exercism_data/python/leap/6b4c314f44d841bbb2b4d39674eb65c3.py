def is_leap_year(year):

	if type(year) != int: return "Not a year."

	if (year % 4 == 0 and year % 100 != 0) or year % 400 == 0:
		return True
	else:
		return False
