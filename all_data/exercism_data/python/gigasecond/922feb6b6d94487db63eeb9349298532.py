from datetime import date, timedelta

def add_gigasecond(bday):
	'''
	Find your gigasecond birtday!
	'''

	# add 10**9 seconds to the input date
	return bday + timedelta(seconds=1e9)
