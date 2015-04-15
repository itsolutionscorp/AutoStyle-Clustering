from datetime import timedelta

def add_gigasecond(d):
	return timedelta(0, 10**9, 0) + d
