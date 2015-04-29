__author__ = 'benlarue'


def is_leap_year(inp):
	import calendar
	if calendar.isleap(inp):
		return True
	else:
		return False
