from datetime import date

def add_gigasecond(a_date):
	return date.fromordinal(int(a_date.toordinal() + 1e9 / 60 / 60 / 24))
