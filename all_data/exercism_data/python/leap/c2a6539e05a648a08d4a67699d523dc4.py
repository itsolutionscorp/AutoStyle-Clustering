def is_leap_year(year):
	return (not year % 400) or (not year % 4 and bool(year % 100))
