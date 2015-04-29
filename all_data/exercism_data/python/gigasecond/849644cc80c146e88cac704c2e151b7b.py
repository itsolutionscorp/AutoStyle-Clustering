import datetime

def add_gigasecond(startingdate):
	gigabday = startingdate + datetime.timedelta(seconds=1000000000)
	return gigabday
