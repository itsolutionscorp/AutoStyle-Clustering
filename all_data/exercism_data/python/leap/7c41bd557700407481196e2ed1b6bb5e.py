def is_leap_year(year):
	if not isinstance(year, int):
		return False

	## Not a leap year if it isn't divisible by 4.
	if year % 4 != 0:
		return False

	## Leap year if it's divisible by 400.
	if year % 400 == 0:
		return True
	
	## Not leap year if it isn't divisible by 400 but is by 100.
	if year % 100 == 0:
		return False
	
	## Leap year otherwise -- divisible by 4, but not by 400 or 100.
	return True
