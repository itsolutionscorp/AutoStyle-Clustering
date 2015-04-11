def is_leap_year(year):
	is_leap_year = False

	if year % 4 == 0:
		is_leap_year = True
		if year % 100 == 0:
			is_leap_year = False
			if year % 400 == 0:
				is_leap_year = True

	return is_leap_year
