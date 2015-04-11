#
# Returns true if year is leapyear
# Conditions:
# Year is divisible by 4
# Year is not divisible by 100 unless also divisible by 400


def is_leap_year(year):
	if not (year % 4 == 0):
		return False
	elif (year % 100 == 0) and not (year % 400 == 0):
		return False
	return True
