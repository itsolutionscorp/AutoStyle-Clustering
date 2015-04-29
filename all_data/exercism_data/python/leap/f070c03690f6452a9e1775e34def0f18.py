def is_leap_year(year):
	y = float(year)
	if (y / 4 == int(y/4) and y / 100 != int(y/100)) or y / 400 == int(y/400):
		return True
	else:
		return False
