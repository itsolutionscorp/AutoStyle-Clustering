import datetime
def add_gigasecond(date):
	"""
	Input: Date in the form of datetime(year, month, day, hour, minute, second)
	Output: Date in the form of datetime, 10^9 seconds later.
	"""
	return date + datetime.timedelta(seconds=10**9)
