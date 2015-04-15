import datetime

def add_gigasecond(starting_date):
	delta = datetime.timedelta(seconds=10**9)
	return starting_date + delta
