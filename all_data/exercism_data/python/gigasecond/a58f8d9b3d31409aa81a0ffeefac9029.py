import time
import datetime
from datetime import date

def add_gigasecond(tm = date(year = 1970, month = 1, day = 1)):
	
	delta_date = datetime.datetime(tm.year, tm.month, tm.day, 0, 0, 0) + datetime.timedelta(seconds = 10**9)

	return date(delta_date.year, delta_date.month, delta_date.day)

#add_gigasecond(date(1959, 3, 23))
