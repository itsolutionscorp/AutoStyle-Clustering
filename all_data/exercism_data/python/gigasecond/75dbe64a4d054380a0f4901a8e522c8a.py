import datetime

def add_gigasecond(tm = datetime.date(year = 1970, month = 1, day = 1)):
	
	delta_date = datetime.datetime(tm.year, tm.month, tm.day, 0, 0, 0) + datetime.timedelta(seconds = 10**9)

	return datetime.date(delta_date.year, delta_date.month, delta_date.day)
