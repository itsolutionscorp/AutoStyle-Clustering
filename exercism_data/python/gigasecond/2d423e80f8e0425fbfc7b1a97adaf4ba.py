from datetime import timedelta

def add_gigasecond(date):
	gigasecond_days = timedelta(seconds=1000000000)
	return date + gigasecond_days
