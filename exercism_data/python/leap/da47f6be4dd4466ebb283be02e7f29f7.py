def is_leap_year(year):
	# If the year is divisible by 4 and not 100.
	if (year%4==0 and not year%100==0):
		return True
	# Or if the year is divisble by 400.
	if (year%400==0):
		return True

	return False
