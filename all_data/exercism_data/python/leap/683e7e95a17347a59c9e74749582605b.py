def is_leap_year(arg):
	if arg % 400 == 0:
		return True
	elif arg % 100 == 0:
		return False
	elif arg % 4 == 0:
		return True
	else:
		return False
