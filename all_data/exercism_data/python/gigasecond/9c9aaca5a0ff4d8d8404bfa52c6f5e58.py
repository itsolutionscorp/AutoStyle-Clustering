from datetime import date

def add_gigasecond(d):
	'''
	Calculate the date that someone turned or will celebrate their 1 Gs anniversary
	'''
	return date.fromordinal((int) (d.toordinal() + 1e9//(24*60*60)));
