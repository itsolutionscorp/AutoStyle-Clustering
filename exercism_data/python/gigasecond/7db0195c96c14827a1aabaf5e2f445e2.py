# gigasecond.py
# import date and timedelta from datetime
from datetime import date
from datetime import timedelta

def add_gigasecond(birthday):
	# add given date to one gigasecond
	return birthday + timedelta(seconds = 10 ** 9)
