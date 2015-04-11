def is_leap_year(year, is_leap=None):
	divisible_by_100 = year % 100 == 0
	# Assumes anything divisible by 4 is also divisible by 400
	divisible_by_4 = year % 4 == 0
	divisible_by_400 = year % 400 == 0

	if divisible_by_4 and not divisible_by_100:
		is_leap = True
	elif divisible_by_400:
		is_leap = True
	else:
		is_leap = False

	return is_leap
