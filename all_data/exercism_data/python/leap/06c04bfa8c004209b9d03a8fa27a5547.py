def is_leap_year(x):
	return (x % 4 == 0) - (x % 100 == 0) + (x % 400 == 0) > 0
