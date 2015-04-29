from datetime import timedelta

def add_gigasecond(date_in):
	gigasecond = timedelta(seconds = 10**9)
	date_out = date_in + gigasecond
	return date_out
