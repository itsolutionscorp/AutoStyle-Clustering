from datetime import datetime, date

def add_gigasecond(date_in):
	sec = int(datetime.strftime(date_in, "%s"))
	return datetime.date(datetime.fromtimestamp(sec + 10**9))
