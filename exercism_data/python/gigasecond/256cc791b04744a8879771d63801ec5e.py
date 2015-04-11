import datetime
from datetime import timedelta

def add_gigasecond(date):
	if isinstance(date, datetime.date):
		return date + timedelta(seconds=10**9)
