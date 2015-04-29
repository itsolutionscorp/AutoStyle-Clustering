def is_leap_year(year):
	if not bool(not year % 4) or bool(not year % 100) and not bool(not year % 400):
		return False
	return True
