from datetime import date
from datetime import datetime
from datetime import timedelta

def add_gigasecond(date_input):
	dt_input = datetime(date_input.year, date_input.month, date_input.day)
	dt = dt_input + (datetime.fromtimestamp(10**9) - datetime(1970, 1, 1) + timedelta(1, 0, 0))
	return dt.date()
