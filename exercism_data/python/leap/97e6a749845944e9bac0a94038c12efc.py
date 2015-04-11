def is_leap_year(year):
	is_leap = False
	if year % 100 == 0 and year % 400 != 0:
		is_leap = False
	elif year % 4 == 0:
		is_leap = True
	return is_leap
