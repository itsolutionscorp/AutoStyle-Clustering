# this is a method that takes a year and returns True if the year is a 
# leap year and false otherwise

def is_leap_year(year):
	if year % 4 == 0:
		if year % 400 == 0:
			return True
		elif year % 100 > 0:
			return True
		else:
			return False
