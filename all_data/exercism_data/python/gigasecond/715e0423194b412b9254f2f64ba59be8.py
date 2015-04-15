from datetime import timedelta

def add_gigasecond(startdate):
	return startdate + timedelta(seconds=1000000000)
