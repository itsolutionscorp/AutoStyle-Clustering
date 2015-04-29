from datetime import timedelta

def add_gigasecond(startdate):
	'''Given a start date, calculates the date on which
	a gigasecond has passed'''
	timediff = timedelta(seconds=10**9)
	return (startdate + timediff)
