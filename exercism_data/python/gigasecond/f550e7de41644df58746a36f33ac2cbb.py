from datetime import date, timedelta

def add_gigasecond(birthday):
	return birthday + timedelta(seconds=10**9)
