from datetime import datetime, timedelta

def add_gigasecond(t):
	return t + timedelta(seconds = 10 ** 9)
