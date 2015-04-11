'''
Test a given number for whether or not it is a leap year based on the given rules:
on every year that is evenly divisible by 4
except every year that is evenly divisible by 100
unless the year is also evenly divisible by 400
'''

def is_leap_year(year):
	if not year % 4:
		if not year % 100:
			if not year % 400:
				return True
			else:
				return False
		else:
			return True
	else:
		return False
