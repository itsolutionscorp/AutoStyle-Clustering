from datetime import date

def add_gigasecond(d):
	return date.fromordinal(d.toordinal() + 1000000000 / 86400)
