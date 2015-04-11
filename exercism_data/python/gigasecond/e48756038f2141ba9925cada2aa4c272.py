import datetime
GIGA = 10**9

def add_gigasecond(origdate):
	return origdate + datetime.timedelta(seconds = GIGA)
