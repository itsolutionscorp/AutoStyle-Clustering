def is_leap_year(self):
	if self % 4 == 0:
		if self % 100 == 0:
			if self % 400 == 0:
				return True # divisible by 400
			else:
				return False # divisible by 100 but not 400
		return True # only divisible by 4
