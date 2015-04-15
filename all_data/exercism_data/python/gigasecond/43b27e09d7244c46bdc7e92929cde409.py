from datetime import date 
import time
def add_gigasecond(initial_second):
	nd = time.mktime(initial_second.timetuple())+(10**9)
	newdate = date.fromtimestamp(nd)
	return newdate
