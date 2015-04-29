#
# Skeleton file for the Python "Leap" exercise.
#
def is_leap_year(year):
	# Start by dividing year by 4 to check basic assertion
	# Then check if divisible by 100
	if year % 4 == 0:
		if year % 100 == 0:
			if year % 400 == 0:
				return True
			else:
				return False
		# If divisible by 4, but not by 100 (and hence not by 400 either), return True.
		return True
	else:
		return False
