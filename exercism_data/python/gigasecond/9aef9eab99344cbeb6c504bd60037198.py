from datetime import timedelta
from datetime import date

gigasecond = timedelta(seconds=10**9)

def add_gigasecond(date):
	return date + gigasecond
