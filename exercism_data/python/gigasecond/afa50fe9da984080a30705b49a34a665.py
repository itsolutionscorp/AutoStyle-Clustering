import time
import datetime

def add_gigasecond(birthday):
	seconds = (birthday-datetime.datetime(1970, 1, 1)).total_seconds()
	stamp = time.gmtime(10**9+seconds)

	return datetime.datetime(stamp.tm_year, stamp.tm_mon, stamp.tm_mday, stamp.tm_hour, stamp.tm_min, stamp.tm_sec)
