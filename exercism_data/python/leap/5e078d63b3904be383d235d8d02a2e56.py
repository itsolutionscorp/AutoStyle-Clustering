#
# Skeleton file for the Python "Leap" exercise.
#

def is_leap_year(year):
	if not year % 4:
		if not year % 100:
			if not year % 400:
				return True #div by 100 and 400 - true
			else:
				return False #div by 100, not 400 - false
		else:
			return True #div by 4, not 100 - true
	else:
		return False #not div by 4 - false
