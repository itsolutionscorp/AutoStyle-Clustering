from datetime import date
from datetime import timedelta

def add_gigasecond(date):
	gigasecond = 1000000000
	gigasecond_days = int(((gigasecond / 3600) / 24))
	date = date + timedelta(days=gigasecond_days)
	return date
	


