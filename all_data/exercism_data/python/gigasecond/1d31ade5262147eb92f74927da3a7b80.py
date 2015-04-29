# gigasecond
from datetime import date, timedelta

def add_gigasecond(date):
	gigasec = timedelta(seconds = 10**9)
	return date + gigasec
