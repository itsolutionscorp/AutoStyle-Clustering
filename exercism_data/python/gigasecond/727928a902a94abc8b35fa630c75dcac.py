from datetime import timedelta

gigasecond = timedelta(seconds = 10**9)

def add_gigasecond(date):
	giga_anniversary = date + gigasecond
	return giga_anniversary
