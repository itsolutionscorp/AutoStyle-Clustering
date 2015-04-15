import datetime

def add_gigasecond(dt):
	return dt + datetime.timedelta(0, 10**9) # days, seconds
