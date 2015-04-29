def is_leap_year(years):
	if years%4 == 0:
		if years%100 == 0 and not years%400 == 0:
			return False
		return True
	return False
