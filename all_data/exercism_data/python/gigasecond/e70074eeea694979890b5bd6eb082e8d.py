from datetime import timedelta

def add_gigasecond(date_in):
	return date_in + timedelta(seconds = 10**9)
