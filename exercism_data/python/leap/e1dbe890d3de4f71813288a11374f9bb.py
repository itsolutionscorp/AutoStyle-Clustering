def is_leap_year(year):
	# Validation to know if is a leap year return true
	if year % 4 == 0 and year % 100 != 0 or year % 400 == 0:
		return True
	else:
		return False
