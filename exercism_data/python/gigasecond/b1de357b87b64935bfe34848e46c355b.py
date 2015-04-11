import datetime

def add_gigasecond(dateTimeObj):
	a = dateTimeObj + datetime.timedelta(0,10**9)
	return a
