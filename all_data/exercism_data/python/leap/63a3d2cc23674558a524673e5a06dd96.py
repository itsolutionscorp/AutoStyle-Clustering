
def is_leap_year(year):
	if not year % 4 == 0:
		return False
	elif not year % 100 == 0:
		return True
	elif not year % 400 == 0:
		return False
	else:
		return True
