def is_leap_year(year):
	# Input validation
	if not isinstance(year, (int, long)):
		raise ValueError("Year must be a valid integer")

	if year % 4 == 0:
		if year % 100 == 0 and not year % 400 == 0:
			return False
		else:
			return True
	else:
		return False
