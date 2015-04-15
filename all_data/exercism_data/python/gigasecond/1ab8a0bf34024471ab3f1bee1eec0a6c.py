from datetime import datetime, timedelta

def add_gigasecond(data):
	sec = timedelta(seconds = 10**9)
	return data + sec
