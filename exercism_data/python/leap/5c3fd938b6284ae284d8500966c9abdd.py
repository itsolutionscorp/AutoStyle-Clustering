def is_leap_year(year):
	# except every year that is evenly divisible by 100
	if year % 100 == 0:
		# unless the year is also evenly divisible by 400
		if year % 400 == 0:
			return True
		else:
			return False
	# on every year that is evenly divisible by 4
	elif year % 4 == 0:
		return True
	else:
		return False