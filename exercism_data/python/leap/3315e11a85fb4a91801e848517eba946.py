def is_leap_year(yearTest):

	if yearTest % 100 == 0:
		if yearTest % 400 == 0:
			return True
		return False

	elif yearTest % 4 == 0:
		return True

	return False
