def is_leap_year(year):
	return (year % 100 > 0 or year % 400 == 0) and year % 4 == 0
