from datetime import date
import time

def add_gigasecond(day):
	return date.fromtimestamp( time.mktime(day.timetuple()) + int(1e9))
