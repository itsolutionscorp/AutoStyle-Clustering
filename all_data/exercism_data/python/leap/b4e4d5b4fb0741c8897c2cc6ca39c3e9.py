def is_leap_year(argument):
	
	if not argument % 4:
		
		if not argument % 400:
			return True

		elif not argument % 100:
			return False

		else:
			return True

	else:
		return False
