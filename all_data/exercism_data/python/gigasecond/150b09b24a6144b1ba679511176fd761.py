from datetime import datetime,timedelta
from numpy import power

def add_gigasecond(arg):
	''' Calculates a gigasecond (10^9) later date
	Arguments:
	- arg - datetime object representing initial date and time

	Returns:
	- datetime object that is exactly 10**9 seconds later than arg
	'''
	if type(arg) is not datetime:
		raise TypeError('arg must be a datetime, not a %s' % type(arg))
	return arg + timedelta(seconds=power(10,9))


# Standalone debugging
if __name__ == '__main__':
	print(add_gigasecond(datetime(2015, 1, 24, 23, 59, 59)))
