def is_leap_year(year):
	if year%4 == 0:							# Leap year if evenly divisible by 4
		if year%100 == 0:					# Except if evenly divisible by 100
			if year%400 == 0: return True	# Unless alse evenly divisible by 400
			else: return False
		else: return True
	else: return False


# # Leap

# Write a program that will take a year and report if it is a leap year.

# The tricky thing here is that a leap year occurs:
#	on every year that is evenly divisible by 4
#	except every year that is evenly divisible by 100
#	unless the year is also evenly divisible by 400

# For example, 1997 is not a leap year, but 1996 is.  1900 is not a leap
# year, but 2000 is.

# If your language provides a method in the standard library that does
# this look-up, pretend it doesn't exist and implement it yourself.
