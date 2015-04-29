from datetime import timedelta

A_GIGASECOND = timedelta(seconds=10**9)

def add_gigasecond(date):
	return date + A_GIGASECOND
