import datetime

def add_gigasecond(dateobj):
	bdate = (dateobj - datetime.date(1970,1,1)).total_seconds()
	return datetime.date.fromtimestamp(bdate+1e9)
