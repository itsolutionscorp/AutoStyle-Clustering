import datetime
import math
def add_gigasecond(date):
	days = int(1E9/(3600*24))
	seconds = int(1E9)-days*3600*24
	return (date+datetime.timedelta(days, seconds))
