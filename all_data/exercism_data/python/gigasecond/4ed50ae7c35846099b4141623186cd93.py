import datetime

def add_gigasecond(origdate):
	return origdate + datetime.timedelta(seconds = 10**9)
