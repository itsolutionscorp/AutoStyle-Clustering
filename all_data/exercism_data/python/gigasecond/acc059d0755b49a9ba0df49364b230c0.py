import datetime

def add_gigasecond(date):
	giga_second = datetime.timedelta(seconds = 10**9)
	return date + giga_second
