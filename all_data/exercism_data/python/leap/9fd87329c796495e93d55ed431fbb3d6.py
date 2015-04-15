def is_leap_year(year):
	return year % 400 == 0 if year % 100 == 0 else year % 4 == 0
