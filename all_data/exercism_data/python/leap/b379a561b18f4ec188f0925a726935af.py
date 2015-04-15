#
# Skeleton file for the Python "Bob" exercise.
#
def is_leap_year(year):
	if year <= 1582:
		return False
	elif year % 400 == 0:
		return True
	elif year % 100 == 0:
		return False
	elif year % 4 == 0:
		return True
	else:
		return False
