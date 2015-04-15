from datetime import date
from datetime import timedelta

def add_gigasecond(date_born):
	days_in_gigasecond = 10**9 / (60 * 60 * 24)
	ann_date = date_born + timedelta(days = days_in_gigasecond)
	return ann_date
