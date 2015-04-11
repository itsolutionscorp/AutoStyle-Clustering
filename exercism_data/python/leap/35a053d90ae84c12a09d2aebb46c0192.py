def is_leap_year(y):
	if y % 4 == 0:
		if y % 400 == 0:
			return True
		if y % 100 == 0:
			return False
		return True
	else:
		return False
