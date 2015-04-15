def is_leap_year(year=0):
	if year % 400 == 0:
		return True
	if year % 100 == 0:
		return False
	if year %   4 == 0:
		return True
	if year %   1 == 0:
		return False
	raise TypeError
