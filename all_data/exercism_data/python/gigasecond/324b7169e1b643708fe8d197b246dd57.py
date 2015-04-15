import datetime

def add_gigasecond (x):
	y = datetime.timedelta(seconds=10**9)
	return x + y
