from datetime import timedelta

def add_gigasecond(day):
	gs=timedelta(seconds=(10**9))
	newday=day+gs
	return newday
