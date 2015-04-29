#
# Skeleton file for the Python "Bob" exercise.
#
def is_leap_year(year):

	if year <= 1582:
		leap_year = False
	elif year % 400 == 0:
		leap_year = True
	elif year % 100 == 0:
		leap_year = False
	elif year % 4 == 0:
		leap_year = True
	else:
		leap_year = False
	return leap_year
