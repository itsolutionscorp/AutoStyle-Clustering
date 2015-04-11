def is_leap_year(year):

	if int(year) % 100 == 0 and int(year) % 400 != 0:
		return False
	elif int(year) % 4 == 0:
		return True
	else:
		return False
