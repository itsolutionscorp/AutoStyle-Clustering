from datetime import timedelta

def add_gigasecond(date_input):
	return date_input + timedelta(0, 10**9, 0)
