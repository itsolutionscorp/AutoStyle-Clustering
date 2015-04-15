import datetime
import time

def add_gigasecond(date):
	GigaBday_timestamp = (to_timestamp(date)+ 10**9)
	return to_date(GigaBday_timestamp)

def to_timestamp(date):
	dtt = date.timetuple()
	return time.mktime(dtt)

def to_date(timestamp):
	dt_from_timestamp = datetime.datetime.fromtimestamp(timestamp)
	print dt_from_timestamp.timetz()
	return dt_from_timestamp.date()
