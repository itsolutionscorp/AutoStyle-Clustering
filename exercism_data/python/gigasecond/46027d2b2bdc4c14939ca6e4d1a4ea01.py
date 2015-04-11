from datetime import timedelta

td = (10**9)

def add_gigasecond(startdate):
	return startdate + timedelta(seconds=td)
