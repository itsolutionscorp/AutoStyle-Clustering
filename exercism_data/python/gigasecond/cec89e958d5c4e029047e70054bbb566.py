import datetime

def add_gigasecond(givendate):
	"""
	Calculates the date that is 1 Gs (gigaseconds) after the given date.
	"""
	return givendate+datetime.timedelta(0, 1e9)
