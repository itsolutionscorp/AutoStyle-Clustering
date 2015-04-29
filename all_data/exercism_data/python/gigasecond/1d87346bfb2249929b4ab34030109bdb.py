from datetime import timedelta


def add_gigasecond(date):
	giga = 10**9
	return date + timedelta(seconds=giga)
