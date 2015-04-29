from datetime import datetime, timedelta

def add_gigasecond(d):
	return (datetime(d.year, d.month, d.day) + timedelta(seconds=10 ** 9)).date()
