def is_leap_year(year):
	if year % 4 == 0:

		mod100 = (year % 100 == 0)
		if year % 400 == 0 and mod100:
			return True
		if mod100:
			return False
			
		return True

	return False
