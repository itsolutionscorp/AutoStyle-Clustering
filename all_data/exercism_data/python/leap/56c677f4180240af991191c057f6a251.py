def is_leap_year(y):
	return ((y % 100 != 0) or (y % 400 == 0)) and y % 4 == 0
