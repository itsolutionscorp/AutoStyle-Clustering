def is_leap_year(year):
	leap_year = True
	if year % 4 != 0:
		leap_year = False

	elif year % 100 != 0:
		leap_year = True

	elif year % 400 != 0:
		leap_year = False

	else:
		leap_year = True

	return leap_year
