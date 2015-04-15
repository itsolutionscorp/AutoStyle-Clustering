# computes the date one gigasecond from the input date

import datetime

def add_gigasecond(startDate):
	return startDate + datetime.timedelta(seconds=10**9)

