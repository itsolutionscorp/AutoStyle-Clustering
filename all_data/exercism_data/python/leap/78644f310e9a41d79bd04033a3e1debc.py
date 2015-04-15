def is_leap_year(argument):
	
	if not argument % 400:
		return True

	elif argument % 100:
		if not argument % 4:
			return True
		else:
			return False

	else:
		return False
