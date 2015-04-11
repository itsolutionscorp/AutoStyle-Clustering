def is_leap_year(self):
	if self % 400 == 0:
		return True
	elif self % 100 == 0:
		return False
	elif self % 4 == 0:
		return True
	else:
		return False	
