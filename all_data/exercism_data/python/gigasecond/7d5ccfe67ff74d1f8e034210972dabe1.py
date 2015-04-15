from datetime import datetime, timedelta

def add_gigasecond(date):
	gigasecond = 10**9
	return date + timedelta(seconds=gigasecond)
