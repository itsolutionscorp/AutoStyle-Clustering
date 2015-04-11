def is_leap_year(some_input):
	if (some_input % 400) == 0:
		return True
	elif (some_input % 100) == 0:
		return False
	elif (some_input % 4) == 0:
		return True
	else:
		return False
