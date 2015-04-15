def is_leap_year(self):
	if self % 4 == 0:
		if self % 100 == 0:
			if self % 400 == 0:
				return True
			return False
		return True
