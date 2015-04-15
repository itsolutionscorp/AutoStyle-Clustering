""" test if a year is leap or not and return a boolean """


def is_leap_year(year):
	if year % 4 == 0 and year % 100 != 0: # the year is divisible by 4 not by 100
		return True

	elif year % 400 == 0: # the year is divisible by 400
		return True

	return False
