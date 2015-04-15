def is_leap_year(y):
	if ((y % 100) == 0):
		if((y % 400) == 0):
			return True
		else:
			return False
	if ((y % 4) == 0):
		return True
