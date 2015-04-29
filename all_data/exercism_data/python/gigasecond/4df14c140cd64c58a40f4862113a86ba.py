import datetime
def add_gigasecond(date):
	d = datetime.timedelta(seconds=10**9)
	return date + d
