from datetime import date

def add_gigasecond(d):
	return date.fromordinal((int) (d.toordinal() + 1e9//(24*60*60)));
