from datetime import date
from datetime import timedelta

def add_gigasecond(theday):
	delta = timedelta(days=11574)
	return theday + delta
