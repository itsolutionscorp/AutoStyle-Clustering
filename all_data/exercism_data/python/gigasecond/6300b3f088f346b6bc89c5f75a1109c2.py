from datetime import date, timedelta
def add_gigasecond(date):
	giga_anniversary = date + timedelta(seconds=1000000000)
	return giga_anniversary
