#!/usr/bin/python

# Check if year is leap year
def is_leap_year(year):
	
	# True if evenly divisible by 4 
	if ((year % 4) == 0):
		
		# False if evenly divisible by 100 but no 400
		if ((year % 100) == 0) and ((year % 400) != 0):
			return False
		else:
			return True		
	else:
		return False
