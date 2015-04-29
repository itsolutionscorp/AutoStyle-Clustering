import time
from datetime import datetime

def add_gigasecond(gb):
	gbs = time.mktime(gb.timetuple()) + float(pow(10,9))
	test= time.localtime(gbs)
	return datetime(test[0],test[1],test[2],test[3],test[4],test[5])
