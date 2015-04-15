from datetime import date
from datetime import timedelta

def add_gigasecond(date):
	d = timedelta (days = 11574, hours = 1, minutes = 46, seconds = 42)
	gigaDate = date + d
	return gigaDate
