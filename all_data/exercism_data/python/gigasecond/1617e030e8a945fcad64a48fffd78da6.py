import datetime

def add_gigasecond(date):
	t = datetime.timedelta(seconds = 10**9)

	return date + t
