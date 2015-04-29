from datetime import timedelta
def add_gigasecond(date):
	gigasecond = timedelta(seconds=1000000000)
	return date+gigasecond
