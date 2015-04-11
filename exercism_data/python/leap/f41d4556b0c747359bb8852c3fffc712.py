#!/usr/bin/python

# Check to see if a date is a leap year.

def is_leap_year(year):
	check4 = float(year) / 4
	# Initial check is just to see if the year is divisible by 4. If not it will fail.
	if check4.is_integer():
		check400 = float(year) / 400
		check100 = float(year) / 100
		#If the year is divisible by 4 and 400 then it is a leap year.
		if check400.is_integer():
			return True
		# If the year is divisible by 4, but also divisible by 100 then is it not a leap year.
		elif check100.is_integer():
			return False
		# Otherwise it is a leap year (just divisible by 4). 
		else:
			return True
	else:
		return False
