def is_leap_year(y):
	if y % 400 == 0:
		return True
	elif y % 100 == 0:
		return False
	elif y % 4 == 0:
		return True
	else:
		return False
