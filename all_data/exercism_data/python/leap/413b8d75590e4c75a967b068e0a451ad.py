def is_leap_year(a):
	if a % 4 == 0:
		if a % 100 == 0:
			if a % 400 == 0:
				return True
			else:
				return False
		else:
			return True
	else:
		return False
