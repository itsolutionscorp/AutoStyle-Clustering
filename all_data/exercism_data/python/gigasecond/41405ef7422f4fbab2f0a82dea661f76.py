from datetime import timedelta

def add_gigasecond(original):
	return original + timedelta(seconds=10**9)
