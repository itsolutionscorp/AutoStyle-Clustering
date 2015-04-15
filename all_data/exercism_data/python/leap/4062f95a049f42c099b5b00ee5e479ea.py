def is_leap_year(year):
	isIt = True
	if year % 4 != 0:
		isIt = False
	if year % 100 == 0 and year % 400 != 0:
		isIt = False
	return isIt
