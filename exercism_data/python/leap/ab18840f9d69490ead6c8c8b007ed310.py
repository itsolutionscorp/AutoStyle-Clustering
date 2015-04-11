def is_leap_year(year):
	# Input validation
	if not isinstance(year, (int, long)):
		return False

	if year % 4 == 0:
		if year % 100 == 0:
			if year % 400 == 0:
				return True
			else:
				return False
		else:
			return True

	return False
