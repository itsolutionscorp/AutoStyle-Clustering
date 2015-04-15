from datetime import timedelta as delta

def add_gigasecond(date):
	return date + delta(seconds = 10**9)
