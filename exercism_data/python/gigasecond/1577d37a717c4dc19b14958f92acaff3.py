from datetime import datetime
import time

# Not sure why this is failing, its always off by an hour but
# not consistently in the same direction. Is it time-zone related?
def add_gigasecond(date):
	return datetime.fromtimestamp(time.mktime(date.timetuple()) + (10**9))
