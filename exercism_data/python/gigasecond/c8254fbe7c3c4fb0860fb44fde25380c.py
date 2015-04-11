from datetime import date, timedelta

def add_gigasecond(date):
	return date+timedelta(seconds=10**9)

print add_gigasecond(date(2010,3,10))
