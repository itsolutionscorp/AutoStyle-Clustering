from datetime import timedelta

GIGASECOND= timedelta(seconds = 10**9)

def add_gigasecond(date):
	return date + GIGASECOND
