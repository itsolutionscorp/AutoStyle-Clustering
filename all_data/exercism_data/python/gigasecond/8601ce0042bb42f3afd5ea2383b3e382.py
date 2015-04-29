import datetime

# add 1 gigasecond to a given date
def add_gigasecond(date_start):
	# one gigasecond in days
	days_to_add = 10**9 / 3600 / 24
	date_end = date_start+datetime.timedelta(days=days_to_add)

	return date_end
