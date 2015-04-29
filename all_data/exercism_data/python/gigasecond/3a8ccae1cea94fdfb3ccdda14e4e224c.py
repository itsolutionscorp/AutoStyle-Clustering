from datetime import datetime, timedelta

def add_gigasecond(d):
	time = datetime(d.year, d.month, d.day)
	return (time + timedelta(seconds=10**9)).date()
