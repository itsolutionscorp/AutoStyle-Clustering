#
# Skeleton file for the Python "Leap" exercise.
#

# Also solvable with 3 nested if's

def is_leap_year(year):
	if 	year%4 == 0 and year%100 == 0 and year%400 == 0\
			or \
			year%4 == 0 and year%100 != 0:
		return True
	else:
		return False
