import datetime
import math

def add_gigasecond(date_):
	delta = datetime.timedelta(seconds=math.pow(10,9))#days, seconds
	return date_+delta
