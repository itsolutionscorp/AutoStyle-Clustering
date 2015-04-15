def is_leap_year(year):

	# If year is divisible by 400 it
	# is not a leap year
	if year%400 == 0:
		return True
	
	# If year is divisible by 4 and
	# not by 100 it is a leap year
	if year%4 == 0 and year%100 != 0:
		return True
	
	# All other years are not leap years
	return False  
