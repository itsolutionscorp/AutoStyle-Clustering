def is_leap_year(year):
	if year % 400 == 0:
		return True
	if year % 4 > 0:
		return False
	if year % 25 == 0:
		return False
	return True
