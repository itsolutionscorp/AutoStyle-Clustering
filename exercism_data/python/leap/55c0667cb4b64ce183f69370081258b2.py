def is_leap_year(year):

	# fail case one: not divisible by 4
	if year % 4 != 0:
		return False

	# fail case two: divisible by 4, divisible by 100, but not divisible by 400
	elif year % 100 == 0 and year % 400 != 0:
		return False

	else:
		return True
