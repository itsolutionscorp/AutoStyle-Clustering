'''This module will take in a year (positive integer) and return True if the
	year is a leap year, False if it is not.
'''

def is_leap_year(year):
	
	modulo4 = year%4
	modulo100 = year%100
	modulo400 = year%400

	if modulo4 == 0:
		if  modulo100 == 0:
			if modulo400 == 0:
				return True
			else:
				return False
		else:
			return True
	else:
		return False
