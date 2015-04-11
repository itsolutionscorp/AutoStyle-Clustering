from datetime import date

def add_gigasecond(x):
	return date.fromordinal(date.toordinal(x)+11574)
