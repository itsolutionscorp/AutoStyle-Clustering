def is_leap_year(year):
	if (year % 100 == 0 and year % 400 != 0):
		return False
	elif (year % 4 != 0):
		return False
	else:
		return True