import datetime

# add 1 gigasecond to a given date
def add_gigasecond(date_start):
	date_end = date_start+datetime.timedelta(seconds=10**9)

	return date_end
