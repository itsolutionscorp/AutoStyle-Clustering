from datetime import timedelta

def add_gigasecond(date_given):
	return date_given + timedelta(seconds=10**9)
