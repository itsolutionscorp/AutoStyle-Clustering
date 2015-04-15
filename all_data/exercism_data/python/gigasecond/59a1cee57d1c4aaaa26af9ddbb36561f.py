import datetime
from datetime import date

def add_gigasecond(d):
	a = datetime.datetime(d.year, d.month, d.day)
	b = a + datetime.timedelta(0,10**9)
	return b.date()
