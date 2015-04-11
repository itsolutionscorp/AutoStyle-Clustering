import datetime

def add_gigasecond(date):
	date = date + datetime.timedelta(seconds = 1000000000)

	return date
