def is_leap_year(year):
	if year % 4 == 0:
		if not year % 100 == 0:
			return True
		elif year % 100 == 0 and year % 400 == 0:
			return True
		else:
			return False
	else:
		return False
