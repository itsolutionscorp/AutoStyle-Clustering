from datetime import date
from datetime import timedelta

def add_gigasecond(gigDate):
	return gigDate + timedelta(seconds=10**9)
