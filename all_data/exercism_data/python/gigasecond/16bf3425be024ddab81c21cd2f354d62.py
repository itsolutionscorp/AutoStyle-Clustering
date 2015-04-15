from datetime import timedelta

def add_gigasecond(iso_date):
	return iso_date + timedelta(seconds = 10 ** 9)


