def is_leap_year(year):
	'''Test for leap year
	Must be divisible by 4 but NOT divisible by 100
	UNLESS also divisible by 400
	'''
	# is year divisible by 4?
	if year % 4 == 0:
		# is year not divisible by 100 or divisible by 400?
		if year % 100 != 0 or year % 400 == 0:
			return True
	# If not divisible by 4, then false
	return False
