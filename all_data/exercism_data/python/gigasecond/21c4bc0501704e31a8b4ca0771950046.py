import datetime

def add_gigasecond(date):
	seconds = 10**9
	days = (seconds/3600)/24
	return date + datetime.timedelta(days=days)
