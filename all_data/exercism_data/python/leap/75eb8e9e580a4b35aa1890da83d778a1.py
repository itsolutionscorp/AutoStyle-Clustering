def is_leap_year(year):

	if year % 4 is 0:
		if year % 100 is 0 and year % 400 is not 0:
			return False
		else:
			return True
	return False
